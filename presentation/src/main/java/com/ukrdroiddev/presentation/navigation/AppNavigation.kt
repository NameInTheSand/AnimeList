package com.ukrdroiddev.presentation.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController

@Stable
interface AppNavigation {
    val navGraph: NavGraph
    val navController: NavHostController
    val environment: ScreenEnvironment
}

class AppNavigationImpl(
    override val navGraph: NavGraph,
    override val navController: NavHostController,
    environmentState: State<ScreenEnvironment>
) : AppNavigation {
    override val environment: ScreenEnvironment = environmentState.value
}



