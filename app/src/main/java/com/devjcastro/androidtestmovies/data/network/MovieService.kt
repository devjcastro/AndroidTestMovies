package com.devjcastro.androidtestmovies.data.network

import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.dtos.ResponseDTO
import com.devjcastro.androidtestmovies.data.dtos.ResponseGenresDTO
import com.devjcastro.androidtestmovies.data.dtos.ResponseVideosDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<ResponseDTO>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Observable<ResponseDTO>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String): Observable<ResponseDTO>

    @GET("movie/{movieId}")
    fun getDetailMovie(@Path("movieId") movieId: Long, @Query("api_key") apiKey: String): Observable<FilmDTO>

    @GET("movie")
    fun searchMovie(@Query("api_key") apiKey: String, @Query("query") query: String, @Query("include_adult") includeAdult: Boolean): Observable<ResponseDTO>

    @GET("list")
    fun getGenres(@Query("api_key") apiKey: String): Observable<ResponseGenresDTO>

    @GET("movie/{movieId}/videos")
    fun getYoutubeVideos(@Path("movieId") movieId: Long, @Query("api_key") apiKey: String): Observable<ResponseVideosDTO>
}