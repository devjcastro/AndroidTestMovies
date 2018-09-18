package com.devjcastro.androidtestmovies

import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.dtos.YoutubeVideoDTO
import com.devjcastro.androidtestmovies.features.movie.detail.IDetailFilmPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmPresenterTest {

    @Mock
    var filmPresenter: IDetailFilmPresenter? = null

    @Test
    fun detailFilmsSuccess() {

        val film = FilmDTO()
        Mockito.doNothing().`when`(filmPresenter)?.getDetailMovie(Mockito.anyLong())
        Mockito.doNothing().`when`(filmPresenter)?.onSuccessDetailFilm(Mockito.any())

        filmPresenter?.getDetailMovie(1)
        filmPresenter?.onSuccessDetailFilm(film)

        Mockito.verify(filmPresenter, Mockito.times(1))?.onSuccessDetailFilm(film)
    }

    @Test(expected = Exception::class)
    fun detailFilmFail(){
        Mockito.doThrow().`when`(filmPresenter)?.getDetailMovie(Mockito.anyLong())
        filmPresenter?.getDetailMovie(1)
        Mockito.verify(filmPresenter)
    }

    @Test
    fun loadYoutubeVideosSuccess() {

        val youtubeVideos = listOf<YoutubeVideoDTO>()
        Mockito.doNothing().`when`(filmPresenter)?.getAllVideos(Mockito.anyLong())
        Mockito.doNothing().`when`(filmPresenter)?.onSuccessYoutubeVideos(Mockito.anyList())

        filmPresenter?.getAllVideos(1)
        filmPresenter?.onSuccessYoutubeVideos(youtubeVideos)

        Mockito.verify(filmPresenter, Mockito.times(1))?.onSuccessYoutubeVideos(youtubeVideos)
    }


    @Test(expected = Exception::class)
    fun loadYoutubeVideosFail(){
        Mockito.doThrow().`when`(filmPresenter)?.getAllVideos(Mockito.anyLong())
        filmPresenter?.getAllVideos(1)
        Mockito.verify(filmPresenter)
    }
}