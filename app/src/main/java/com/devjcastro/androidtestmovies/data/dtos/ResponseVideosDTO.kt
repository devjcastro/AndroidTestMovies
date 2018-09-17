package com.devjcastro.androidtestmovies.data.dtos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseVideosDTO {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<YoutubeVideoDTO>? = null
}