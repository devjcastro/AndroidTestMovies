package com.devjcastro.androidtestmovies.features.movie.detail

import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.dtos.ResponseVideosDTO
import com.devjcastro.androidtestmovies.data.network.MovieApiFactory
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import com.devjcastro.androidtestmovies.utils.Prefs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IDetailFilmInteractor{
    fun getDetailMovie(movieId: Long)
    fun getAllVideos(filmId: Long)
}

class DetailFilmInteractor: IDetailFilmInteractor {

    interface CallbackInteractor {
        fun onSuccessDetailFilm(film: FilmDTO?)
        fun onSuccessYoutubeVideos(films: ResponseVideosDTO?)
    }

    var listener: CallbackInteractor? = null

    constructor(listener: CallbackInteractor) {
        this.listener = listener
    }

    override fun getDetailMovie(movieId: Long) {

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

    override fun getAllVideos(filmId: Long) {

        val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
        MovieApiFactory.build()
                .getYoutubeVideos(filmId, apiKey!!)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    it?.let {
                        /*val entities = MapUtil.getEntityFromListDto(it)
                        entities?.let {
                            realmDb.insertRows(entities)
                        }
                        listener?.onSuccessListProspects(it)*/
                        listener?.onSuccessYoutubeVideos(it)
                    }
                }

    }
}