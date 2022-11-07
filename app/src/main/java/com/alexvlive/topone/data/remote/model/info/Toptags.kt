package com.alexvlive.topone.data.remote.model.info

data class Toptags(
    val tag: List<Tag>
)

fun Toptags.getTags(): List<String> = tag.map { it.name }