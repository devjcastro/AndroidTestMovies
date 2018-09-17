package com.devjcastro.androidtestmovies.data.db.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CategoryEntity: RealmObject {

    @PrimaryKey
    var id: Int = 0
    var name: String? = null
    var imageResource: Int = 0

    constructor()

    constructor(id: Int, name: String?, imageResource: Int) {
        this@CategoryEntity.id = id
        this@CategoryEntity.name = name
        this@CategoryEntity.imageResource = imageResource
    }

}