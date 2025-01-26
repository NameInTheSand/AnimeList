package com.ukrdroiddev.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import com.ukrdroiddev.presentation.generics.tollbars.AppToolBar
import com.ukrdroiddev.presentation.generics.tollbars.NavigateUpAction
import com.ukrdroiddev.presentation.navigation.AnimeListRoute
import com.ukrdroiddev.presentation.navigation.LocalNavHostController
import com.ukrdroiddev.presentation.navigation.rememberNavigation
import com.ukrdroiddev.presentation.screens.animeList.animeListScreen
import com.ukrdroiddev.presentation.ui.theme.MyAnimeListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAnimeListTheme {
                NavigationScreen()
            }
        }
    }
}

@Composable
private fun NavigationScreen(
) {
    val context = LocalContext.current
    val navigation = rememberNavigation(startDestination = stringResource(AnimeListRoute)) {
        screen(context.getString(AnimeListRoute)) { animeListScreen() }
    }
    val navController = navigation.navController
    val environment = navigation.environment
    val navGraph = navigation.navGraph

    Scaffold(
        topBar = {
            AppToolBar(
                titleRes = environment.titleRes,
                navigateUpAction = if (navController.previousBackStackEntry != null) {
                    NavigateUpAction.Visible {
                        navController.navigateUp()
                    }
                } else {
                    NavigateUpAction.Hidden
                }
            )
        }
    ) { contentPaddings ->
        CompositionLocalProvider(LocalNavHostController provides navController) {
            NavHost(
                navController = navController,
                graph = navGraph,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPaddings)
            )
        }
    }

}