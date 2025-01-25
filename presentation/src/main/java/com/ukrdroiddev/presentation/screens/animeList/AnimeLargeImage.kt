package com.ukrdroiddev.presentation.screens.animeList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ukrdroiddev.domain.entities.AnimeDataUiEntity
import com.ukrdroiddev.presentation.R

@Composable
fun AnimeLargeImage(animeDataUiEntity: AnimeDataUiEntity, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(animeDataUiEntity.images.jpg.largeImageUrl)
            .crossfade(true)
            .build(),
        modifier = modifier,
        contentDescription = animeDataUiEntity.titleEnglish ?: animeDataUiEntity.title,
        loading = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.testTag("loading"))
            }
        },
        contentScale = ContentScale.FillBounds,
        error = {
            Image(
                painter = painterResource(R.drawable.image_placeholder),
                contentScale = ContentScale.Inside,
                modifier = modifier,
                contentDescription = stringResource(R.string.no_image)
            )
        }
    )
}