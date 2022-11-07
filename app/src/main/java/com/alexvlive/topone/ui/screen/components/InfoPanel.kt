package com.alexvlive.topone.ui.screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.alexvlive.topone.ui.theme.MyTheme.colors
import com.alexvlive.topone.ui.theme.MyTheme.dimens
import com.alexvlive.topone.ui.theme.MyTheme.fonts
import com.alexvlive.topone.util.getAbbreviated

@Composable
fun InfoPanel (length: Long?, listeners: Long?, playCount: Long?) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){

        if(length != null && length != 0L){

            val time: String
            val minutes = length / 60000
            val seconds = (length / 1000 - minutes * 60)
            time = "$minutes min $seconds sec"

            IconInfo(imageVector = Icons.Rounded.Refresh)
            TextInfo(text = time)
            Spacer(modifier = Modifier
                .size(15.dp))
        }

        if(listeners != null && listeners != 0L){
            IconInfo(imageVector = Icons.Filled.Person)
            TextInfo(text = listeners.getAbbreviated())
            Spacer(modifier = Modifier
                .size(15.dp))
        }

        if(playCount != null && playCount != 0L){
            IconInfo(imageVector = Icons.Filled.Share)
            TextInfo(text = playCount.getAbbreviated())
        }
    }
}

@Composable
fun IconInfo(imageVector: ImageVector){
    Icon(
        imageVector = imageVector,
        tint = colors.iconPrimary,
        modifier = Modifier
            .size(dimens.dimension_icon),
        contentDescription = "")
}

@Composable
fun TextInfo(text: String){
    Text(
        text = text,
        modifier = Modifier
            .padding(start = dimens.padding_medium),
        fontSize = fonts.font_size_medium,
        color = colors.textPrimary
    )
}