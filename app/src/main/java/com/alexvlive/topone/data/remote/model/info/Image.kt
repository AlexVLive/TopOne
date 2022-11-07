package com.alexvlive.topone.data.remote.model.info

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val url: String,
    val size: String
)