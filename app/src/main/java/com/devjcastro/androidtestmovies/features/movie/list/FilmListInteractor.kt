package com.devjcastro.androidtestmovies.features.movie.list

import com.devjcastro.androidtestmovies.data.db.DBHelper
import com.devjcastro.androidtestmovies.data.db.entities.FilmEntity
import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.network.MovieApiFactory
import com.devjcastro.androidtestmovies.utils.ConstantUtils
import com.devjcastro.androidtestmovies.utils.NetworkUtils
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import com.devjcastro.androidtestmovies.utils.Prefs
import com.devjcastro.androidtestmovies.utils.maps.MapUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IFilmListInteractor {
    fun loadPopularFilms()
    fun loadTopRatedFilms()
    fun loadUpcomingFilms()
}

class FilmListInteractor: IFilmListInteractor {

    interface CallbackInteractor {
        fun onSuccessListFilms(categories: List<FilmDTO>?)
    }

    var listener: CallbackInteractor? = null

    constructor(listener: CallbackInteractor) {
        this.listener = listener
    }

    override fun loadPopularFilms() {

        if(NetworkUtils.isOnline()){

            val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
            MovieApiFactory.build()
                    .getPopularMovies(apiKey!!)
                    ?.subscribeOn(Schedulers.newThread())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe {
                        it?.let {
                            it.results?.let{
                                MapUtil.getFilmEntityFromDto(it, ConstantUtils.CategoryID.POPULAR)?.let {
                                    DBHelper().insertRows(it)
                                }
                            }
                            listener?.onSuccessListFilms(it.results)
                        }
                    }

        }
        else{
            dispatchDtosFromEntities(ConstantUtils.CategoryID.POPULAR)
        }
    }


    override fun loadTopRatedFilms() {

        if(NetworkUtils.isOnline()){

            val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
            MovieApiFactory.build()
                    .getTopRatedMovies(apiKey!!)
                    ?.subscribeOn(Schedulers.newThread())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe {
                        it?.let {
                            it.results?.let{
                                MapUtil.getFilmEntityFromDto(it, ConstantUtils.CategoryID.TOP_RATED)?.let {
                                    DBHelper().insertRows(it)
                                }
                            }
                            listener?.onSuccessListFilms(it.results)
                        }
                    }

        }
        else {
            dispatchDtosFromEntities(ConstantUtils.CategoryID.TOP_RATED)
        }

    }

    override fun loadUpcomingFilms() {

        if(NetworkUtils.isOnline()){
            val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
            MovieApiFactory.build()
                    .getUpcomingMovies(apiKey!!)
                    ?.subscribeOn(Schedulers.newThread())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe {
                        it?.let {
                            it.results?.let{
                                MapUtil.getFilmEntityFromDto(it, ConstantUtils.CategoryID.UPCOMING)?.let {
                                    DBHelper().insertRows(it)
                                }
                            }
                            listener?.onSuccessListFilms(it.results)
                        }
                    }
        }
        else {
            dispatchDtosFromEntities(ConstantUtils.CategoryID.UPCOMING)
        }

    }


    private fun dispatchDtosFromEntities(categoryId: Int) {
        val entities = DBHelper().getRowsByIntColumn("categoryId", categoryId, FilmEntity::class.java)
        entities?.let {
            if(it.isNotEmpty()){
                MapUtil.getFilmDtoFromEntities(it)?.let {
                    listener?.onSuccessListFilms(it)
                }
            }
        }
    }

}