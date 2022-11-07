package com.alexvlive.topone.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexvlive.topone.ui.theme.MyTheme.colors
import com.alexvlive.topone.ui.theme.MyTheme.dimens
import com.alexvlive.topone.ui.theme.MyTheme.fonts

@Composable
fun InfoArtist(artist: String, track: String) {

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = artist,
            fontSize = fonts.font_size_large,
            color = colors.textPrimary
        )

        Text(
            text = track,
            fontSize = fonts.font_size_very_large,
            color = colors.textPrimary,
            modifier = Modifier
                .padding(bottom = dimens.padding_large)
        )
    }
}