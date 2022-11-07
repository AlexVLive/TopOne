package com.alexvlive.topone.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import com.alexvlive.topone.MainViewModel
import com.alexvlive.topone.data.remote.model.TrackInfoFull
import com.alexvlive.topone.ui.theme.MyTheme
import com.alexvlive.topone.util.ColorsDominant
import com.alexvlive.topone.util.PaletteUtil
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainImageBox(
    isPortrait: Boolean,
    trackInfoFull: TrackInfoFull,
    viewModel: MainViewModel,
    navController: NavController,
    colorsDominant: ColorsDominant,
    lmd: (colorsDominant: ColorsDominant) -> Unit
) {

    Box(modifier = Modifier
        .fillMaxHeight( if(isPortrait) 0.6f else 1f)
        .fillMaxWidth(if(isPortrait) 1f else 0.5f),
        contentAlignment = Alignment.Center
    ) {

        GlideImage(
            imageModel = trackInfoFull.imageUrl,
            modifier = Modifier
                .fillMaxSize(),
            success = {
                it.drawable?.let { drawable ->
                    val icon = drawable.toBitmap()
                    Image(
                        bitmap = icon.asImageBitmap(),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                    val upperSideDominantColor: Int =
                        PaletteUtil.getUpperSideDominantColor(icon)
                    val lowerSideDominantColor: Int =
                        PaletteUtil.getLowerSideDominantColor(icon)
                    if(colorsDominant.upperSideDominantColor.toArgb()
                        == Color.Transparent.toArgb()
                        || colorsDominant.lowerSideDominantColor.toArgb()
                        == Color.Transparent.toArgb()){
                        val colDominant = ColorsDominant(
                            upperSideColor = upperSideDominantColor,
                            lowerSideColor = lowerSideDominantColor)
                        lmd.invoke(colDominant)
                        viewModel.isDarkMode.value = !colDominant.isDarkUpperSide
                    }
                }
            },
            failure = {
                IconPlaceHolder(modifier = Modifier.size(MyTheme.dimens.dimension_extra_large))
            }
        )

        Column(modifier = Modifier.fillMaxSize()) {

            DefaultAppBar(
                isShowBack = true,
                colorsDominant = colorsDominant,
                viewModel = viewModel,
                navController = navController)

            Spacer(modifier = Modifier.weight(1f))

            SimilarLikePanel(
                trackInfoFull = trackInfoFull,
                colorsDominant = colorsDominant,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}