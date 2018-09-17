package com.devjcastro.androidtestmovies.di.movie.list

import com.devjcastro.androidtestmovies.features.movie.list.FilmListActivity
import com.devjcastro.androidtestmovies.features.movie.list.FilmListInteractor
import com.devjcastro.androidtestmovies.features.movie.list.FilmListPresenter
import dagger.Component

@Component(modules = arrayOf(FilmListModule::class))
interface FilmListComponent {

    fun inject(filmListActivity: FilmListActivity)
    fun inject(filmListPresenter: FilmListPresenter)
    fun inject(filmListModel: FilmListInteractor)
    //fun inject(categoryListenerPresenter: FilmListInteractor.CallbackInteractor)
}