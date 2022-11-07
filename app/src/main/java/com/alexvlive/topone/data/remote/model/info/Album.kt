package com.alexvlive.topone.data.remote.model.info

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: String,
    val image: List<Image>,
    val mbid: String,
    val title: String,
    val url: String
)