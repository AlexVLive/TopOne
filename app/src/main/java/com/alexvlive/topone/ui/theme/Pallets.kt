package com.alexvlive.topone.ui.theme

import androidx.compose.ui.graphics.Color

val primary = Color(0xFFedfbff)
val textPrimary = Color(0xFF000a12)
val textSecondary = Color.White
val textSelected = Color.Yellow
val iconPrimary = Color(0xFF000a12)
val iconSelected = Color.Yellow

val primaryLight = Color(0xFF263238)
val textPrimaryLight = Color.White
val textSecondaryLight = Color.Black
val textSelectedLight = Color.Yellow
val iconPrimaryLight = Color.White
val iconSelectedLight = Color.Yellow

data class MyColors(
    val primary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textSelected: Color,
    val iconPrimary: Color,
    val iconSelected: Color,

)

val DarkColorPalette = MyColors(
    primary = primary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    textSelected = textSelected,
    iconPrimary = iconPrimary,
    iconSelected = iconSelected,
)

val LightColorPalette = MyColors(
    primary = primaryLight,
    textPrimary = textPrimaryLight,
    textSecondary = textSecondaryLight,
    textSelected = textSelectedLight,
    iconPrimary = iconPrimaryLight,
    iconSelected = iconSelectedLight,
)