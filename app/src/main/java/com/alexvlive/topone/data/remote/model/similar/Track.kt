package com.alexvlive.topone.data.remote.model.similar

data class Track(
    val artist: Artist,
    val duration: Int,
    val image: List<Image>,
    val match: Double,
    val mbid: String,
    val name: String,
    val playcount: Int,
    val streamable: Streamable,
    val url: String
)