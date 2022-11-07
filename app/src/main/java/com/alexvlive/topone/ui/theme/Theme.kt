package com.alexvlive.topone.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun TopOneTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit) {

    val pallets =
        if (isDarkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        }
    val dimens = MyDimens
    val fonts = MyFonts

    CompositionLocalProvider(
        LocalMyColors provides pallets,
        LocalDimens provides dimens,
        LocalFonts provides fonts,
        content = content
    )
}

object MyTheme {
    val colors: MyColors
        @Composable
        get() = LocalMyColors.current

    val dimens: Dimens
        @Composable
        get() = LocalDimens.current

    val fonts: Fonts
        @Composable
        get() = LocalFonts.current
}

val LocalMyColors = staticCompositionLocalOf <MyColors>{ error("No colors provided") }
val LocalDimens = staticCompositionLocalOf <Dimens>{ error("No dimens provided") }
val LocalFonts = staticCompositionLocalOf <Fonts>{ error("No fonts provided") }