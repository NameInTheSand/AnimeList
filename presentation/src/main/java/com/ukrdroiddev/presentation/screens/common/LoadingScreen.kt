package com.ukrdroiddev.presentation.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ukrdroiddev.presentation.R
import com.ukrdroiddev.presentation.generics.textFields.TitleText

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        val rawComposition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.loading)
        )
        Column(modifier = modifier) {
            LottieAnimation(
                composition = rawComposition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.medium_padding) * 4)
                    .testTag(stringResource(R.string.loading)),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium_padding)))
            TitleText(
                text = stringResource(R.string.loading),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(modifier = Modifier.fillMaxSize())
}