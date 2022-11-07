package com.alexvlive.topone.util

import kotlin.math.round

fun Float.roundFloat(decimals: Int): Float {
    var multiplier = 1.0f
    repeat(decimals){multiplier *= 10}
    return round(this * multiplier) / multiplier
}