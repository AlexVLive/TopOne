package com.alexvlive.topone.util

import kotlin.math.pow

fun Long.getAbbreviated(): String = when(this){
        in -1000..1000 -> this.toString()
        else -> {
            var i = 0
            var value = this
            while (value > 1000){
                i++
                value /= 1000
            }
         val res = this.toFloat() / 1000.toFloat().pow(i.toFloat())
         res.roundFloat(1).toString() + mapAbbr[i]
    }
}

val mapAbbr: Map<Int, String> = hashMapOf(
    1 to "K",
    2 to "M",
    3 to "B"
)