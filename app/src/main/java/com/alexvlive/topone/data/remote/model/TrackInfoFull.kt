package com.alexvlive.topone.data.remote.model

data class TrackInfoFull (
    val artist: String,
    val track: String,
    val length: Long? = null,
    val listeners: Long? = null,
    val playCount: Long? = null,
    val imageUrl: String? = null,
    val info: String? = null,
    val tags: List<String>? = null
)
