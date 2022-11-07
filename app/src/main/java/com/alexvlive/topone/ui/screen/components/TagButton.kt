package com.alexvlive.topone.ui.screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alexvlive.topone.ui.theme.MyTheme.colors

@Composable
fun TagButton (tag: String) {
    OutlinedButton(
        onClick = { },
        border = BorderStroke(
            width = 1.dp,
            color = colors.textPrimary
        ),
        shape = RoundedCornerShape(percent = 50),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = colors.textPrimary
        )
    ){

        Text( text = tag )

    }
}