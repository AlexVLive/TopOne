package com.alexvlive.topone.data.remote.model.alltracks

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("#text")
    val text: String,
    val fulltrack: String
)