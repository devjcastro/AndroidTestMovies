package com.devjcastro.androidtestmovies.data.db.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class YoutubeVideoEntity: RealmObject() {

    @PrimaryKey
    var id: String? = null
    var iso_639_1: String? = null
    var iso_3166_1: String? = null
    var key: String? = null
    var name: String? = null
    var site: String? = null
    var size: Long? = null
    var type: String? = null
    var movieId: Long? = null
}