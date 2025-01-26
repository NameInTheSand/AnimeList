package com.ukrdroiddev.presentation.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.presentation.R
import com.ukrdroiddev.presentation.generics.textFields.TitleText
import com.ukrdroiddev.presentation.utils.getErrorMessage

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, error: NetworkError, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        val rawComposition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.error)
        )
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                composition = rawComposition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .testTag(stringResource(R.string.error)),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_padding)))
            TitleText(
                text = error.getErrorMessage(LocalContext.current),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_padding)))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                )
            ) {
                Text(
                    text = stringResource(R.string.retry),
                    color = MaterialTheme.colorScheme.onSecondary
                )

            }
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(modifier = Modifier.fillMaxSize(), error = NetworkError.NO_INTERNET, {})
}