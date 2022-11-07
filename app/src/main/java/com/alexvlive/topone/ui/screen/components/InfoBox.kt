package com.alexvlive.topone.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.alexvlive.topone.data.remote.model.TrackInfoFull
import com.alexvlive.topone.ui.theme.MyTheme.colors
import com.alexvlive.topone.ui.theme.MyTheme.dimens
import com.alexvlive.topone.ui.theme.MyTheme.fonts
import com.alexvlive.topone.util.ColorsDominant

@Composable
fun InfoBox(isPortrait: Boolean,
            trackInfoFull: TrackInfoFull,
            colorsDominant: ColorsDominant
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorsDominant.upperSideDominantColor,
                        colorsDominant.lowerSideDominantColor
                    )
                )
            )
    ){

        Column(
            modifier = Modifier
                .padding(
                    start = dimens.padding_large,
                    top = dimens.padding_large,
                    end = dimens.padding_large
                )
                .verticalScroll(rememberScrollState())
        ) {

            InfoArtist(
                artist = trackInfoFull.artist,
                track = trackInfoFull.track
            )

            InfoPanel(
                length = trackInfoFull.length,
                listeners = trackInfoFull.listeners,
                playCount = trackInfoFull.playCount
            )

            trackInfoFull.info?.let { text ->
                var infoExpanded by remember{ mutableStateOf(false) }

                Text(
                    text = text,
                    fontSize = fonts.font_size_medium,
                    color = colors.textPrimary,
                    modifier = Modifier
                        .padding(top = dimens.padding_large)
                        .clickable {
                            infoExpanded = !infoExpanded
                        },
                    maxLines = if(infoExpanded) {
                        Int.MAX_VALUE
                    } else {
                        if(isPortrait) {
                            2
                        } else {
                            5
                        }
                           },
                    overflow = if(infoExpanded) {
                        TextOverflow.Visible
                    } else {
                        TextOverflow.Ellipsis
                    }
                )

                if(!infoExpanded)
                    Text(
                        text = "(click text to read more)",
                        fontSize = fonts.font_size_medium,
                        color = colors.textPrimary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally),
                        textAlign = TextAlign.Right
                    )
            }

            trackInfoFull.tags?.let { list ->
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(dimens.padding_medium),
                    modifier = Modifier.padding(top = dimens.padding_large)
                ){
                    items(list){ item ->
                        TagButton(tag = item)
                    }
                }
            }
        }
    }
}