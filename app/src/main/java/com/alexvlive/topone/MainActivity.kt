package com.alexvlive.topone

import android.content.res.Configuration
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alexvlive.topone.ui.screen.Screen
import com.alexvlive.topone.ui.screen.dialog.AlertDialogExit
import com.alexvlive.topone.ui.theme.TopOneTheme
import com.alexvlive.topone.util.appComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    companion object{
        const val NAVIGATION_ROUTE = "main"
        const val PARAM_ARTIST = "artist"
        const val PARAM_TRACK = "track"
    }

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        setContent {
            val window: Window = this.window
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

            val isDarkMode = viewModel.isDarkMode

            val view = LocalView.current
            if (!view.isInEditMode){
                SideEffect {
                    WindowCompat.getInsetsController(window, view)
                        .isAppearanceLightStatusBars = isDarkMode.value
                }
            }

            val navController = rememberNavController()

            TopOneTheme(isDarkTheme = isDarkMode.value) {

                AlertDialogExit(viewModel = viewModel)

                NavHost(
                    navController = navController,
                    startDestination = "$NAVIGATION_ROUTE/{$PARAM_ARTIST}/{$PARAM_TRACK}"
                ){

                    composable(
                        route = "$NAVIGATION_ROUTE/{$PARAM_ARTIST}/{$PARAM_TRACK}",
                        arguments = listOf(
                            navArgument(PARAM_ARTIST){type = NavType.StringType},
                            navArgument(PARAM_TRACK){type = NavType.StringType })
                    ){

                        val artist = it.arguments?.getString(PARAM_ARTIST)
                        val track = it.arguments?.getString(PARAM_TRACK)

                        Screen(
                            isPortrait = (resources.configuration.orientation ==
                                    Configuration.ORIENTATION_PORTRAIT),
                            artist = artist,
                            track = track,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
