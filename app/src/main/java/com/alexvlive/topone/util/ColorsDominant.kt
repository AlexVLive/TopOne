package com.alexvlive.topone.util

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils

data class ColorsDominant (
    val upperSideColor: Int,
    val lowerSideColor: Int
    ){

    val upperSideDominantColor: Color = Color(upperSideColor)
    val lowerSideDominantColor: Color = Color(lowerSideColor)

    val isDarkUpperSide: Boolean = isDark(upperSideColor)
    //val isDarkLowerSide: Boolean = isDark(lowerSideColor)

    fun isContrastImage(): Boolean = ColorUtils.calculateLuminance(upperSideColor) -
            ColorUtils.calculateLuminance(lowerSideColor) > 0.7

    private fun isDark(color: Int): Boolean = ColorUtils.calculateLuminance(color) < 0.5}
