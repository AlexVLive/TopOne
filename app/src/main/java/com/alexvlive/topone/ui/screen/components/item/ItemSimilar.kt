package com.alexvlive.topone.ui.screen.components.item

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import com.alexvlive.topone.MainActivity
import com.alexvlive.topone.MainViewModel
import com.alexvlive.topone.common.takeSuccessData
import com.alexvlive.topone.data.remote.model.TrackInfoShort
import com.alexvlive.topone.ui.screen.components.IconPlaceHolder
import com.alexvlive.topone.ui.theme.MyTheme.colors
import com.alexvlive.topone.ui.theme.MyTheme.dimens
import com.alexvlive.topone.ui.theme.MyTheme.fonts
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ItemSimilar (
    trackInfoShort: TrackInfoShort,
    viewModel: MainViewModel,
    navController: NavController) {

    var url by remember{ mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimens.padding_large,
                top = dimens.padding_medium,
                bottom = dimens.padding_medium,
                end = dimens.padding_medium
            )
            .height(dimens.dimension_small)
            .clickable {
                navController.navigate(
                    "${MainActivity.NAVIGATION_ROUTE}/" +
                            "${trackInfoShort.name}/${trackInfoShort.track}"
                )
            }
    ) {

        LaunchedEffect(key1 = Unit, block = {
            if(url == ""){
                this.launch (Dispatchers.IO) {
                    viewModel.getInfo(
                        artist = trackInfoShort.name,
                        track = trackInfoShort.track)
                        .takeSuccessData()?.let { trackInfo ->
                            trackInfo.track?.album?.image?.last()?.url.let {
                                url = it
                            }
                    }
                }
            }
        })

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(dimens.dimension_small)
        ){
            if(url != ""){
                GlideImage(imageModel = url,
                    modifier = Modifier
                        .fillMaxSize(),
                    success = {
                        it.drawable?.let { drawable ->
                            val icon = drawable.toBitmap()
                            Image(
                                bitmap = icon.asImageBitmap(),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(1.dp, colors.textPrimary, CircleShape),
                                contentDescription = ""
                            )
                        }
                    },
                    failure = {
                        IconPlaceHolder(Modifier
                            .size(dimens.dimension_small)
                            .align(Alignment.Center)
                        )
                    }
                )
            }
            else{
                IconPlaceHolder(Modifier
                    .size(dimens.dimension_small)
                    .align(Alignment.Center)
                )
            }
        }

        Column(modifier = Modifier
            .padding(start = dimens.padding_large)
        ){

            Text(
                text = trackInfoShort.name,
                fontSize = fonts.font_size_very_small,
                color = colors.textPrimary
            )

            Text(
                text = trackInfoShort.track,
                fontSize = fonts.font_size_small,
                color = colors.textPrimary
            )
        }
    }
}