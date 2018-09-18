package com.devjcastro.androidtestmovies.features.movie.detail


import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.dtos.YoutubeVideoDTO

/**
 * Created by jorge.castro on 12/24/2017.
 */

interface IDetailFilmView {
    fun showDetailFilm(film: FilmDTO?)
    fun getAllVideos(videos: List<YoutubeVideoDTO>?)
}
