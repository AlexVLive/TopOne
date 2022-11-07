package com.alexvlive.topone.ui.theme

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val font_size_very_small = 12.sp
val font_size_small = 14.sp
val font_size_medium = 16.sp
val font_size_large = 20.sp
val font_size_very_large = 28.sp

val MyFonts = Fonts(
    font_size_very_small = font_size_very_small,
    font_size_small = font_size_small,
    font_size_medium = font_size_medium,
    font_size_large = font_size_large,
    font_size_very_large = font_size_very_large
)

data class Fonts(
    val font_size_very_small: TextUnit,
    val font_size_small: TextUnit,
    val font_size_medium: TextUnit,
    val font_size_large: TextUnit,
    val font_size_very_large: TextUnit
)