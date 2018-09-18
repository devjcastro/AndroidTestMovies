package com.devjcastro.androidtestmovies.utils.maps

import com.devjcastro.androidtestmovies.data.db.entities.FilmEntity
import com.devjcastro.androidtestmovies.data.db.entities.YoutubeVideoEntity
import com.devjcastro.androidtestmovies.data.dtos.FilmDTO
import com.devjcastro.androidtestmovies.data.dtos.YoutubeVideoDTO
import io.realm.RealmList

class MapUtil {
    companion object {

        fun getFilmEntityFromDto(dtos: List<FilmDTO>, categoryId: Int? = null): List<FilmEntity>? {

            val entities = mutableListOf<FilmEntity>()
            dtos.forEach {

                val genres = RealmList<Int>()
                it.genreIds?.forEach {
                    genres.add(it)
                }

               val ent = FilmEntity().apply {
                   id = it.id ?: 0
                   voteCount = it.voteCount
                   voteAverage = it.voteAverage
                   this.categoryId = categoryId
                   video = it.video
                   title = it.title
                   popularity = it.popularity
                   posterPath = it.posterPath
                   originalLanguage = it.originalLanguage
                   originalTitle = it.originalTitle
                   genreIds = genres
                   backdropPath = it.backdropPath
                   adult = it.adult
                   overview = it.overview
                   releaseDate = it.releaseDate
                   homePage = it.homePage

               }
                entities.add(ent)
            }

            return entities
        }

        fun getFilmDtoFromEntities(entities: List<FilmEntity>): List<FilmDTO>? {
            val dtos = mutableListOf<FilmDTO>()
            entities.forEach {
                val dto = FilmDTO().apply {
                    id = it.id ?: 0
                    voteCount = it.voteCount
                    voteAverage = it.voteAverage
                    video = it.video
                    title = it.title
                    popularity = it.popularity
                    posterPath = it.posterPath
                    originalLanguage = it.originalLanguage
                    originalTitle = it.originalTitle
                    genreIds = it.genreIds
                    backdropPath = it.backdropPath
                    adult = it.adult
                    overview = it.overview
                    releaseDate = it.releaseDate
                    homePage = it.homePage

                }
                dtos.add(dto)
            }

            return dtos
        }


        fun getFilmDtoFromEntity(entities: FilmEntity): FilmDTO? {
            val dto = FilmDTO().apply {
                id = entities.id
                voteCount = entities.voteCount
                voteAverage = entities.voteAverage
                video = entities.video
                title = entities.title
                popularity = entities.popularity
                posterPath = entities.posterPath
                originalLanguage = entities.originalLanguage
                originalTitle = entities.originalTitle
                genreIds = entities.genreIds
                backdropPath = entities.backdropPath
                adult = entities.adult
                overview = entities.overview
                releaseDate = entities.releaseDate
                homePage = entities.homePage
            }
            return dto
        }


        fun getYoutubeVideoDtoFromEntities(entities: List<YoutubeVideoEntity>): List<YoutubeVideoDTO>?{
            val dtos = mutableListOf<YoutubeVideoDTO>()
            entities.forEach {
                val dto = YoutubeVideoDTO().apply {
                    id = it.id
                    iso_639_1 = it.iso_639_1
                    iso_3166_1 = it.iso_3166_1
                    key = it.key
                    name = it.name
                    site = it.site
                    size = it.size
                    type = it.type

                }
                dtos.add(dto)
            }

            return dtos
        }


        fun getYoutubeVideoEntityFromDtos(dtos: List<YoutubeVideoDTO>, movieId: Long): List<YoutubeVideoEntity>?{
            val entities = mutableListOf<YoutubeVideoEntity>()
            dtos.forEach {
                val dto = YoutubeVideoEntity().apply {
                    id = it.id
                    iso_639_1 = it.iso_639_1
                    iso_3166_1 = it.iso_3166_1
                    key = it.key
                    name = it.name
                    site = it.site
                    size = it.size
                    type = it.type
                    this.movieId = movieId
                }
                entities.add(dto)
            }

            return entities
        }

    }
}