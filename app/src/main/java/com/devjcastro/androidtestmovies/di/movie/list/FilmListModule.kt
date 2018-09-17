package com.devjcastro.androidtestmovies.di.movie.list

import com.devjcastro.androidtestmovies.features.movie.list.FilmListInteractor
import com.devjcastro.androidtestmovies.features.movie.list.FilmListPresenter
import com.devjcastro.androidtestmovies.features.movie.list.IFilmListInteractor
import com.devjcastro.androidtestmovies.features.movie.list.IFilmListPresenter
import dagger.Module
import dagger.Provides

@Module
class FilmListModule {

    lateinit var interactorListener: FilmListInteractor.CallbackInteractor

    constructor()

    constructor(listener: FilmListInteractor.CallbackInteractor){
        interactorListener = listener
    }

    @Provides
    fun provideFilmListPresenter(): IFilmListPresenter {
        return FilmListPresenter()
    }

    @Provides
    fun provideFilmModel(): IFilmListInteractor {
        return FilmListInteractor(interactorListener)
    }

}