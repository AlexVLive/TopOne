package com.alexvlive.topone.data.remote.model.similar

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("#text")
    val text: String,
    val fulltrack: String
)