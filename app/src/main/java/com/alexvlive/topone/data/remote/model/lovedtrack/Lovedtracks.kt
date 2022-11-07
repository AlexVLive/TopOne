package com.alexvlive.topone.data.remote.model.lovedtrack

import com.google.gson.annotations.SerializedName

data class Lovedtracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)