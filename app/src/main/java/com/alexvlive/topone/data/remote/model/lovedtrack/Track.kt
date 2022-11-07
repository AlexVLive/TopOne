package com.alexvlive.topone.data.remote.model.lovedtrack

data class Track(
    val artist: Artist,
    val date: Date,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)