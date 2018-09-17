package com.devjcastro.androidtestmovies.di.detailfilm

import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmInteractor
import com.devjcastro.androidtestmovies.features.movie.detail.DetailFilmPresenter
import com.devjcastro.androidtestmovies.features.movie.detail.IDetailFilmInteractor
import com.devjcastro.androidtestmovies.features.movie.detail.IDetailFilmPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailFilmModule {
    lateinit var interactorListener: DetailFilmInteractor.CallbackInteractor

    constructor()

    constructor(listener: DetailFilmInteractor.CallbackInteractor){
        interactorListener = listener
    }

    @Provides
    fun provideCategoryPresenter(): IDetailFilmPresenter {
        return DetailFilmPresenter()
    }

    @Provides
    fun provideLoginModel(): IDetailFilmInteractor {
        return DetailFilmInteractor(interactorListener)
    }
}