package com.ukrdroiddev.presentation.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavHostController

interface ScreenEnvironmentBuilder {
    @get:StringRes
    var titleRes: Int
    val navController: NavHostController
}

class ScreenEnvironmentBuilderImpl(
    private val screenEnvironment: ScreenEnvironment,
    override val navController: NavHostController
) : ScreenEnvironmentBuilder {
    override var titleRes: Int by screenEnvironment::titleRes
}
