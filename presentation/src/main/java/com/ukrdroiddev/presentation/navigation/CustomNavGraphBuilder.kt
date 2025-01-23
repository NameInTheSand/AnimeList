package com.ukrdroiddev.presentation.navigation

import androidx.navigation.NavDestinationDsl
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

@NavDestinationDsl
interface CustomNavGraphBuilder {
    fun screen(route: String, block: CustomScreenBuilder.() -> Unit)
}

class CustomNavGraphBuilderImpl(
    private val naGraphBuilder: NavGraphBuilder,
    private val environmentStore: EnvironmentStore,
    private val navController: NavHostController
) : CustomNavGraphBuilder {

    override fun screen(route: String, block: CustomScreenBuilder.() -> Unit) {
        val customScreenBuilder = CustomScreenBuilderImpl(
            route = route,
            naGraphBuilder = naGraphBuilder,
            environmentStore = environmentStore,
            navController = navController
        )
        customScreenBuilder.apply(block)
    }

}
