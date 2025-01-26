package com.ukrdroiddev.presentation

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.ukrdroiddev.presentation.screens.common.LoadingScreen
import org.junit.Test

class LoadingScreenTest {

    private lateinit var context: Context

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testLoadingScreen() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            LoadingScreen()
        }

        onNodeWithTag(context.getString(R.string.loading)).assertIsDisplayed()
        onNodeWithText(context.getString(R.string.loading)).assertIsDisplayed()
    }

}