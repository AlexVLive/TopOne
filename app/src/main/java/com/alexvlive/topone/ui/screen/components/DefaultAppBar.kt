package com.alexvlive.topone.ui.screen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.alexvlive.topone.MainViewModel
import com.alexvlive.topone.ui.theme.MyTheme.dimens
import com.alexvlive.topone.ui.theme.MyTheme.colors
import com.alexvlive.topone.util.ColorsDominant
import com.alexvlive.topone.util.DialogExitParameters

@Composable
fun DefaultAppBar(
    isShowBack: Boolean,
    colorsDominant: ColorsDominant,
    viewModel: MainViewModel,
    navController: NavController
){
    Column() {

        Spacer(
            modifier = Modifier.height(dimens.dimension_status_bar)
        )

        Row(
            modifier = Modifier.background(Color.Transparent)
        ){

            if(isShowBack){
                IconButton(
                    modifier = Modifier.padding(dimens.padding_large),
                    onClick = {
                        if (!navController.popBackStack())
                            viewModel.isShowDialogExit.value = DialogExitParameters(
                                isShowDialog = true,
                                colorsDominant = colorsDominant
                            )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        tint = colors.iconPrimary,
                        contentDescription = "")
                }
            }

            Spacer(modifier = Modifier.weight(weight = 1f))

            IconButton(
                modifier = Modifier.padding(dimens.padding_large),
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = colors.iconPrimary,
                    contentDescription = "")
            }
        }
    }
}