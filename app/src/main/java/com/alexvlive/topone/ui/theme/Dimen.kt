package com.alexvlive.topone.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val padding_small = 5.dp
val padding_medium = 10.dp
val padding_large = 20.dp

val dimension_status_bar = 24.dp
val dimension_similar = 270.dp

val dimension_small = 48.dp
val dimension_large = 96.dp
val dimension_extra_large = 120.dp

val dimension_corner_shape = 24.dp

val dimension_icon = 22.dp

val MyDimens = Dimens(
    padding_small = padding_small,
    padding_medium = padding_medium,
    padding_large = padding_large,
    dimension_status_bar = dimension_status_bar,
    dimension_similar = dimension_similar,
    dimension_small = dimension_small,
    dimension_large = dimension_large,
    dimension_extra_large = dimension_extra_large,
    dimension_corner_shape = dimension_corner_shape,
    dimension_icon = dimension_icon
)

data class Dimens(
    val padding_small: Dp,
    val padding_medium: Dp,
    val padding_large: Dp,
    val dimension_status_bar: Dp,
    val dimension_similar: Dp,
    val dimension_small: Dp,
    val dimension_large: Dp,
    val dimension_extra_large: Dp,
    val dimension_corner_shape: Dp,
    val dimension_icon: Dp
)