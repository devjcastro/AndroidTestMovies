package com.devjcastro.androidtestmovies.features.movie.list

import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.network.MovieApiFactory
import com.devjcastro.androidtestmovies.utils.PreferencesUtils
import com.devjcastro.androidtestmovies.utils.Prefs
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

        val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
        MovieApiFactory.build()
                .getPopularMovies(apiKey!!)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    it?.let {
                        /*val entities = MapUtil.getEntityFromListDto(it)
                        entities?.let {
                            realmDb.insertRows(entities)
                        }
                        listener?.onSuccessListProspects(it)*/
                        listener?.onSuccessListFilms(it.results)
                    }
                }
    }

    override fun loadTopRatedFilms() {

        val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
        MovieApiFactory.build()
                .getTopRatedMovies(apiKey!!)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    it?.let {
                        /*val entities = MapUtil.getEntityFromListDto(it)
                        entities?.let {
                            realmDb.insertRows(entities)
                        }
                        listener?.onSuccessListProspects(it)*/
                        listener?.onSuccessListFilms(it.results)
                    }
                }

    }

    override fun loadUpcomingFilms() {

        val apiKey = Prefs.getString(PreferencesUtils.KeyPreferences.API_KEY, "")
        MovieApiFactory.build()
                .getUpcomingMovies(apiKey!!)
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    it?.let {
                        /*val entities = MapUtil.getEntityFromListDto(it)
                        entities?.let {
                            realmDb.insertRows(entities)
                        }
                        listener?.onSuccessListProspects(it)*/
                        listener?.onSuccessListFilms(it.results)
                    }
                }

    }

}