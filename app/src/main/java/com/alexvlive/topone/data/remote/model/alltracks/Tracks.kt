package com.alexvlive.topone.data.remote.model.alltracks

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<TrackX>
)