package com.alexvlive.topone.data.remote.model.lovedtrack

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("@text")
    val text: String,
    val size: String
)