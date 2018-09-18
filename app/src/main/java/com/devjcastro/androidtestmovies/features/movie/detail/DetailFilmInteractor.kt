package com.devjcastro.androidtestmovies.features.movie.detail

import com.devjcastro.androidtestmovies.data.db.DBHelper
import com.devjcastro.androidtestmovies.data.db.entities.FilmEntity
import com.devjcastro.androidtestmovies.data.db.entities.YoutubeVideoEntity
import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.dtos.YoutubeVideoDTO
import com.devjcastro.androidtestmovies.network.MovieApiFactory
import com.devjcastro.androidtestmovies.utils.NetworkUtils
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import com.devjcastro.androidtestmovies.utils.Prefs
import com.devjcastro.androidtestmovies.utils.maps.MapUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IDetailFilmInteractor{
    fun getDetailMovie(movieId: Long)
    fun getAllVideos(filmId: Long)
}

class DetailFilmInteractor: IDetailFilmInteractor {

    interface CallbackInteractor {
        fun onSuccessDetailFilm(film: FilmDTO?)
        fun onSuccessYoutubeVideos(films: List<YoutubeVideoDTO>?)
    }

    var listener: CallbackInteractor? = null

    constructor(listener: CallbackInteractor) {
        this.listener = listener
    }

    override fun getDetailMovie(movieId: Long) {

        if(NetworkUtils.isOnline()){

            val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
            MovieApiFactory.build()
                    .getDetailMovie(movieId, apiKey!!)
                    ?.subscribeOn(Schedulers.newThread())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe {
                        it?.let {
                            /*val entities = MapUtil.getEntityFromListDto(it)
                            entities?.let {
                                realmDb.insertRows(entities)
                            }
                            listener?.onSuccessListProspects(it)*/
                            listener?.onSuccessDetailFilm(it)
                        }
                    }
        }
        else{
            dispatchDtoFromEntity(movieId)
        }

    }

    override fun getAllVideos(filmId: Long) {

        if(NetworkUtils.isOnline()){
            val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
            MovieApiFactory.build()
                    .getYoutubeVideos(filmId, apiKey!!)
                    ?.subscribeOn(Schedulers.newThread())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe {
                        it?.let {
                            it.results?.let {
                                MapUtil.getYoutubeVideoEntityFromDtos(it, filmId)?.let {
                                    DBHelper().insertRows(it)
                                }
                                listener?.onSuccessYoutubeVideos(it)
                            }
                        }
                    }
        }
        else {
            dispatchYoutubeVideosDtoFromEntities(filmId)
        }
    }

    private fun dispatchDtoFromEntity(movieId: Long) {
        val entities = DBHelper().getRowsByLongColumn("id", movieId, FilmEntity::class.java)
        entities?.let {
            if(it.isNotEmpty()){
                it.first()?.let {
                    MapUtil.getFilmDtoFromEntity(it)?.let {
                        listener?.onSuccessDetailFilm(it)
                    }
                }
            }
        }
    }


    private fun dispatchYoutubeVideosDtoFromEntities(movieId: Long){
        val entities = DBHelper().getRowsByLongColumn("movieId", movieId, YoutubeVideoEntity::class.java)
        entities?.let {
            it?.let {
                MapUtil.getYoutubeVideoDtoFromEntities(it)?.let {
                    listener?.onSuccessYoutubeVideos(it)
                }
            }
        }
    }

}