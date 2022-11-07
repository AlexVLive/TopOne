package com.alexvlive.topone.ui.screen.dialog

import android.app.Activity
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.alexvlive.topone.MainViewModel
import com.alexvlive.topone.ui.theme.MyTheme
import com.alexvlive.topone.util.DialogExitParameters
import kotlin.system.exitProcess

@Composable
fun AlertDialogExit(
    viewModel: MainViewModel
) {

    val dialogExitParameters by viewModel.isShowDialogExit
    val activity = (LocalContext.current as? Activity)

    if(dialogExitParameters.isShowDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.isShowDialogExit.value = getCancelParametersDialog()
            },
            backgroundColor = if(dialogExitParameters.colorsDominant?.isContrastImage() != false){
                dialogExitParameters.colorsDominant?.upperSideDominantColor ?: Color.Transparent
            }
            else{
                dialogExitParameters.colorsDominant?.lowerSideDominantColor ?: Color.Transparent
                },
            contentColor = MyTheme.colors.textPrimary,
            title = {
                Text(
                    text = "Exit",
                    color = MyTheme.colors.textPrimary,
                    fontSize = 20.sp
                )
            },
            text = {
                Text(
                    text = "Do you really want to exit?",
                    color = MyTheme.colors.textPrimary,
                    fontSize = 20.sp
                )
            },
            confirmButton = {

                IconButton(
                    onClick = {
                        viewModel.isShowDialogExit.value = getCancelParametersDialog()
                        activity?.finish()
                        exitProcess(0)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        tint = MyTheme.colors.iconPrimary,
                        contentDescription = ""
                    )
                }
            },
            dismissButton = {
                IconButton(
                    onClick = {
                        viewModel.isShowDialogExit.value = getCancelParametersDialog()
                    }
                ) {
                    Icon(
                        Icons.Filled.Close,
                        tint = MyTheme.colors.iconPrimary,
                        contentDescription = ""
                    )
                }
            }
        )
    }
}

private fun getCancelParametersDialog() = DialogExitParameters(
    isShowDialog = false,
    colorsDominant = null
)