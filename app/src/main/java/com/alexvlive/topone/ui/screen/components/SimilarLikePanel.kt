package com.alexvlive.topone.ui.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.alexvlive.topone.MainViewModel
import com.alexvlive.topone.data.remote.model.TrackInfoFull
import com.alexvlive.topone.ui.theme.MyTheme.colors
import com.alexvlive.topone.ui.theme.MyTheme.dimens
import com.alexvlive.topone.ui.theme.MyTheme.fonts
import com.alexvlive.topone.util.ColorsDominant

@Composable
fun SimilarLikePanel (trackInfoFull: TrackInfoFull,
                      colorsDominant: ColorsDominant,
                      viewModel: MainViewModel,
                      navController: NavController
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimens.padding_large)
    ) {
        DropDownSimilar(
            similarFlow = viewModel.similarFlow,
            colorsDominant = colorsDominant,
            viewModel = viewModel,
            navController = navController
        ) {
            viewModel.fetchSimilar(
                artist = trackInfoFull.artist,
                track = trackInfoFull.track
            )
        }

        Spacer(
            modifier = Modifier
                .weight(1f)
        )

        var isFavorite by remember {
            mutableStateOf(viewModel.isLovedTrack(
                artist = trackInfoFull.artist,
                track = trackInfoFull.track))
        }

        val colorBackground = if (colorsDominant.isContrastImage()) {
            colorsDominant.upperSideDominantColor
        }
        else {
            colorsDominant.lowerSideDominantColor
        }
        ExtendedFloatingActionButton(
            modifier = Modifier.height(dimens.dimension_small),
            onClick = {
                isFavorite = !isFavorite
                if(isFavorite){
                    viewModel.addLoveTrack(
                        artist = trackInfoFull.artist,
                        track = trackInfoFull.track
                    )
                }
                else{
                    viewModel.removeLoveTrack(
                        artist = trackInfoFull.artist,
                        track = trackInfoFull.track
                    )
                }
            },
            icon = {
                Icon(
                    Icons.Filled.Favorite,
                    tint =
                    if(isFavorite) {
                        colors.iconSelected
                    }
                    else {
                        colors.textPrimary
                    },
                    contentDescription = ""
                )
            },
            backgroundColor = colorBackground.copy(alpha = 0.7f),
            text = {
                Text(
                    text = "Like",
                    color = colors.textPrimary,
                    fontSize = fonts.font_size_medium,
                    style = MaterialTheme.typography.body1
                )
            }
        )
    }
}