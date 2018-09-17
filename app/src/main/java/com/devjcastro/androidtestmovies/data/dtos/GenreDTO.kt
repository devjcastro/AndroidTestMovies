package com.devjcastro.androidtestmovies.data.dtos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenreDTO {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}