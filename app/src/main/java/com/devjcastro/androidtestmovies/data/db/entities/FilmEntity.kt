package com.devjcastro.androidtestmovies.data.db.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class FilmEntity: RealmObject() {

    @PrimaryKey
    var id: Long = 0
    var voteCount: Int? = null
    var categoryId: Int? = null
    var video: Boolean? = null
    var voteAverage: Double? = null
    var title: String? = null
    var popularity: Double? = null
    var posterPath: String? = null
    var originalLanguage: String? = null
    var originalTitle: String? = null
    var genreIds: RealmList<Int>? = null
    var backdropPath: String? = null
    var adult: Boolean? = null
    var overview: String? = null
    var releaseDate: String? = null
    var homePage: String? = null
}