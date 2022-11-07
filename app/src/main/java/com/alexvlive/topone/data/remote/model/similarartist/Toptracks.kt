package com.alexvlive.topone.data.remote.model.similarartist

import com.google.gson.annotations.SerializedName

data class Toptracks(
    @SerializedName("#attr")
    val attr: Attr,
    val track: List<Track>
)