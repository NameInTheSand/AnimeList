package com.ukrdroiddev.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph

@Composable
fun rememberNavigation(
    startDestination: String,
    builder: CustomNavGraphBuilder.() -> Unit
): AppNavigation {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: ""

    val environmentStore = remember { EnvironmentStore() }
    val environment = remember(currentRoute) {
        environmentStore[currentRoute].invoke()
    }

    val environmentState = rememberUpdatedState(newValue = environment)

    return remember {
        val navGraph = navController.createGraph(startDestination) {
            val navGraphBuilder: NavGraphBuilder = this
            val customScreenBuilder = CustomNavGraphBuilderImpl(
                naGraphBuilder = navGraphBuilder,
                environmentStore = environmentStore,
                navController = navController
            )
            customScreenBuilder.apply(builder)
        }
        AppNavigationImpl(
            navController = navController,
            environmentState = environmentState,
            navGraph = navGraph
        )
    }
}
