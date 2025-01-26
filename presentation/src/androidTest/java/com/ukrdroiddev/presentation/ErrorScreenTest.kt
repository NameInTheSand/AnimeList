package com.ukrdroiddev.presentation

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.presentation.screens.common.ErrorScreen
import org.junit.Test

class ErrorScreenTest {

    private lateinit var context: Context

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testRetryButtonAndLottie() = runComposeUiTest {
        var retryIterator = 0
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.REQUEST_TIMEOUT, onRetry = { retryIterator++ })
        }
        onNodeWithText(context.getString(R.string.retry)).assertIsDisplayed()
        assert(retryIterator == 0)
        onNodeWithText(context.getString(R.string.retry)).performClick()
        assert(retryIterator == 1)
        onNodeWithTag(context.getString(R.string.error)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testRequestTimeout() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.REQUEST_TIMEOUT, onRetry = {})
        }
        onNodeWithText(context.getString(R.string.request_timeout)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testNoInternet() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.NO_INTERNET, onRetry = { })
        }

        onNodeWithText(context.getString(R.string.no_internet)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testServerError() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.SERVER_ERROR, onRetry = { })
        }
        onNodeWithText(context.getString(R.string.server_error)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testUrlNotFound() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.URL_NOT_FOUND, onRetry = { })
        }
        onNodeWithText(context.getString(R.string.url_not_found)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testValidationError() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.VALIDATION_ERROR, onRetry = { })
        }
        onNodeWithText(context.getString(R.string.validation_error)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testLimit() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.LIMIT, onRetry = { })
        }
        onNodeWithText(context.getString(R.string.request_limit)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testForbidden() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.FORBIDDEN, onRetry = { })
        }
        onNodeWithText(context.getString(R.string.forbidden)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testUnauthorized() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.UNAUTHORIZED, onRetry = { })
        }
        onNodeWithText(context.getString(R.string.unauthorized)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testUnknown() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            ErrorScreen(error = NetworkError.UNKNOWN, onRetry = { })
        }
        onNodeWithText(context.getString(R.string.unknown_error)).assertIsDisplayed()
    }

}