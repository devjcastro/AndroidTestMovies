package com.devjcastro.androidtestmovies.data.dtos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseGenresDTO {

    @SerializedName("genres")
    @Expose
    var genres: List<GenreDTO>? = null
}