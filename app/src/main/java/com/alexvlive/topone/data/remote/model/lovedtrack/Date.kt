package com.alexvlive.topone.data.remote.model.lovedtrack

import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("@text")
    val text: String,
    val uts: String
)