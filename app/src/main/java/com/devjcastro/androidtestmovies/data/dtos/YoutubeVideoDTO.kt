package com.devjcastro.androidtestmovies.data.dtos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class YoutubeVideoDTO {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("iso_639_1")
    @Expose
    var iso_639_1: String? = null

    @SerializedName("iso_3166_1")
    @Expose
    var iso_3166_1: String? = null

    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("site")
    @Expose
    var site: String? = null

    @SerializedName("size")
    @Expose
    var size: Long? = null

    @SerializedName("type")
    @Expose
    var type: String? = null
}