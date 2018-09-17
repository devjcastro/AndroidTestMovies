package com.devjcastro.androidtestmovies.di.detailfilm

import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmActivity
import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmInteractor
import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmPresenter
import com.devjcastro.androidtestmovies.features.movie.detail.sections.DetailFragment
import dagger.Component

@Component(modules = arrayOf(DetailFilmModule::class))
interface DetailFilmComponent {
    fun inject(detailFragment: DetailFragment)
    fun inject(detailFilmActivity: DetailFilmActivity)
    fun inject(detailFilmPresenter: DetailFilmPresenter)
    fun inject(detailFilmModel: DetailFilmInteractor)
    fun inject(detailFilmListenerPresenter: DetailFilmInteractor.CallbackInteractor)
}