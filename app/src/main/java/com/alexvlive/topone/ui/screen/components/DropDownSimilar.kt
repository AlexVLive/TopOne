package com.alexvlive.topone.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexvlive.topone.MainViewModel
import com.alexvlive.topone.common.ResultResponse
import com.alexvlive.topone.common.takeSuccessData
import com.alexvlive.topone.data.remote.model.TrackInfoShort
import com.alexvlive.topone.ui.screen.components.item.ItemSimilar
import com.alexvlive.topone.ui.theme.MyTheme.colors
import com.alexvlive.topone.ui.theme.MyTheme.dimens
import com.alexvlive.topone.ui.theme.MyTheme.fonts
import com.alexvlive.topone.util.ColorsDominant
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DropDownSimilar (
    similarFlow: StateFlow<ResultResponse<List<TrackInfoShort>>>,
    viewModel: MainViewModel,
    navController: NavController,
    colorsDominant: ColorsDominant,
    lmdOpen: () -> Unit
) {

    var isOpen by remember { mutableStateOf(false) }

    val simFlow by similarFlow.collectAsState()

    Box(modifier = Modifier
        .height(height = dimens.dimension_small)
        .width(dimens.dimension_extra_large)
        .clip(
            if (isOpen) RoundedCornerShape(
                topStart = dimens.dimension_corner_shape,
                topEnd = dimens.dimension_corner_shape
            )
            else RoundedCornerShape(
                size = dimens.dimension_corner_shape
            )
        )

    ){

        val colorBackground = if (colorsDominant.isContrastImage()) {
            colorsDominant.upperSideDominantColor
        }
        else {
            colorsDominant.lowerSideDominantColor
        }
        Surface(
            color =
            if(isOpen){ colorBackground }
            else { colorBackground.copy(alpha = 0.7f) },
            modifier = Modifier
                .fillMaxSize()
        ){

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {

                Row(
                    modifier = Modifier.wrapContentHeight()
                ){

                    Text(
                        text =  "Similar",
                        modifier = Modifier
                            .padding(start = dimens.padding_large),
                        color = colors.textPrimary,
                        fontSize = fonts.font_size_medium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector =
                        if (isOpen){ Icons.Filled.KeyboardArrowUp }
                        else{ Icons.Filled.KeyboardArrowDown },
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = dimens.padding_large)
                            .size(dimens.dimension_icon)
                            .clickable {
                                isOpen = !isOpen
                                if (isOpen){
                                    lmdOpen()
                                }
                            },
                        tint = colors.textPrimary
                    )

                    DropdownMenu(
                        expanded = isOpen,
                        onDismissRequest = { isOpen = false },
                        modifier = Modifier
                            .background(color = colorBackground)
                            .width(dimens.dimension_similar)
                            .height(dimens.dimension_similar),
                        offset = DpOffset(x = 0.dp, y = 15.dp)
                    ) {

                        simFlow.takeSuccessData()?.let { similarTracks ->

                            Box(
                                modifier = Modifier
                                .width(dimens.dimension_similar)
                                .height(dimens.dimension_similar)
                            ){

                                val state = rememberLazyListState()
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(dimens.padding_medium),
                                    state = state
                                ){
                                    items(similarTracks){ item ->
                                        ItemSimilar(
                                            trackInfoShort = item,
                                            viewModel = viewModel,
                                            navController = navController
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}