package com.alexvlive.topone.ui.screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alexvlive.topone.ui.theme.MyTheme.colors

@Composable
fun IconPlaceHolder(modifier: Modifier){
    Icon(
        imageVector = Icons.Filled.Star,
        tint = colors.textSecondary,
        modifier = modifier
            .clip(CircleShape)
            .border(1.dp, colors.textSecondary, CircleShape),
        contentDescription = ""
    )
}