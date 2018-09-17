package com.devjcastro.androidtestmovies.features.movie.list

import com.devjcastro.androidtestmovies.base.mvp.IBaseView
import com.devjcastro.androidtestmovies.data.dtos.FilmDTO

interface IFilmListView: IBaseView {
    fun loadFilms(films: List<FilmDTO>)
}