package com.alexvlive.topone.data.remote.model.similar

import com.google.gson.annotations.SerializedName

data class SimilartracksX(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)