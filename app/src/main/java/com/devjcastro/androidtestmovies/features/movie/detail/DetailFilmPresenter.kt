package com.devjcastro.androidtestmovies.features.movie.detail

import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.dtos.ResponseVideosDTO
import com.devjcastro.androidtestmovies.di.detailfilm.DaggerDetailFilmComponent
import com.devjcastro.androidtestmovies.di.detailfilm.DetailFilmModule
import com.intergrupo.pruebaintergrupo.base.mvp.BasePresenter
import com.intergrupo.pruebaintergrupo.base.mvp.IBasePresenter
import javax.inject.Inject

interface IDetailFilmPresenter: IBasePresenter<IDetailFilmView>{
    fun getDetailMovie(filmId: Long)
    fun getAllVideos(filmId: Long)
}

class DetailFilmPresenter: BasePresenter<IDetailFilmView>, IDetailFilmPresenter, DetailFilmInteractor.CallbackInteractor {

    @Inject
    lateinit var detailFilmModel: IDetailFilmInteractor

    constructor() {
        DaggerDetailFilmComponent.builder().detailFilmModule(DetailFilmModule(this)).build().inject(this)
    }

    override fun getDetailMovie(filmId: Long) {
        detailFilmModel.getDetailMovie(filmId)
    }

    override fun getAllVideos(filmId: Long) {
        detailFilmModel.getAllVideos(filmId)
    }

    override fun onSuccessDetailFilm(film: FilmDTO?) {
        view?.showDetailFilm(film)
    }

    override fun onSuccessYoutubeVideos(films: ResponseVideosDTO?) {
        view?.getAllVideos(films)
    }
}