package com.ukrdroiddev.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

interface CustomScreenBuilder {
    fun content(block: @Composable () -> Unit)
    fun environment(block: ScreenEnvironmentBuilder.() -> Unit)
}

class CustomScreenBuilderImpl(
    private val route: String,
    private val naGraphBuilder: NavGraphBuilder,
    private val environmentStore: EnvironmentStore,
    private val navController: NavHostController
) : CustomScreenBuilder {

    override fun content(block: @Composable () -> Unit) {
        naGraphBuilder.composable(route) { block() }
    }

    override fun environment(block: ScreenEnvironmentBuilder.() -> Unit) {
        val environmentProvider = {
            val screenEnvironment = ScreenEnvironmentImpl()
            val screenEnvironmentBuilder = ScreenEnvironmentBuilderImpl(
                screenEnvironment = screenEnvironment,
                navController = navController
            )
            screenEnvironmentBuilder.apply(block)
            screenEnvironment
        }
        environmentStore[route] = environmentProvider
    }

}