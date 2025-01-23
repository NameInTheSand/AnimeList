package com.ukrdroiddev.presentation.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalNavHostController = staticCompositionLocalOf<NavController> {
    error("Can't access nav controller")
}