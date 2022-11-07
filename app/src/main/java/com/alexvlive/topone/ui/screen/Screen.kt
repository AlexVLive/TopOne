package com.alexvlive.topone.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import com.alexvlive.topone.MainViewModel
import com.alexvlive.topone.common.takeSuccessData
import com.alexvlive.topone.data.remote.model.TrackInfoFull
import com.alexvlive.topone.ui.screen.components.*
import com.alexvlive.topone.util.ColorsDominant
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Screen(isPortrait: Boolean,
           artist: String?,
           track: String?,
           viewModel: MainViewModel,
           navController: NavController) {

    var trackInfoFull by remember {
        mutableStateOf<TrackInfoFull?>(null)
    }

    var colorsDominant by remember{
        mutableStateOf(ColorsDominant(Color.Transparent.toArgb(), Color.Transparent.toArgb()))
    }

    LaunchedEffect(key1 = Unit, block = {
        if(artist != null && track != null){
            trackInfoFull = viewModel.getInfoFull(artist = artist, track = track)
        }
        else{
            viewModel.topTrack.onEach {
                it.takeSuccessData()?.let { topTrackInfoFull ->
                    trackInfoFull = viewModel.getInfoFull(
                        artist = topTrackInfoFull.artist,
                        track = topTrackInfoFull.track
                    )
                }
            }.launchIn(this)
        }
    })

    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    colorsDominant.lowerSideDominantColor,
                    colorsDominant.upperSideDominantColor

                )
            )
        )
    ) {

        trackInfoFull?.let { trackInfoFull ->

            if(isPortrait){
                Column {
                    MainImageBox(
                        isPortrait = true,
                        trackInfoFull = trackInfoFull,
                        viewModel = viewModel,
                        navController = navController,
                        colorsDominant = colorsDominant,
                        lmd = {colorsDominant = it}
                    )

                    InfoBox(
                        isPortrait = true,
                        trackInfoFull = trackInfoFull,
                        colorsDominant = colorsDominant
                    )
                }
            }
            else{
                Row {
                    MainImageBox(
                        isPortrait = false,
                        trackInfoFull = trackInfoFull,
                        viewModel = viewModel,
                        navController = navController,
                        colorsDominant = colorsDominant,
                        lmd = {colorsDominant = it}
                    )

                    InfoBox(
                        isPortrait = false,
                        trackInfoFull = trackInfoFull,
                        colorsDominant = colorsDominant
                    )
                }

            }
        }
    }
}