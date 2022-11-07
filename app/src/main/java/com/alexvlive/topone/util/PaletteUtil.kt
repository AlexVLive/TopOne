package com.alexvlive.topone.util

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

object PaletteUtil {

    private const val defaultValue = 0xFFFFFF

    fun getUpperSideDominantColor(bitmap: Bitmap): Int {
        val builder = Palette.Builder(bitmap)
            .setRegion(0, 0, bitmap.width, bitmap.height / 2)
        return builder.generate().getDominantColor(defaultValue)
    }

    fun getLowerSideDominantColor(bitmap: Bitmap): Int {
        val builder = Palette.Builder(bitmap)
            .setRegion(0, bitmap.height / 2, bitmap.width, bitmap.height)
        return builder.generate().getDominantColor(defaultValue)
    }
}

