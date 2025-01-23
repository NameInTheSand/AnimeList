package com.ukrdroiddev.presentation.navigation

import androidx.annotation.StringRes
import com.ukrdroiddev.presentation.R

interface ScreenEnvironment {
    @get:StringRes
    var titleRes: Int
}

class ScreenEnvironmentImpl : ScreenEnvironment {
    override var titleRes: Int = R.string.all_anime

    companion object {
        val DEFAULT = ScreenEnvironmentImpl()
    }
}