package com.ukrdroiddev.presentation.screens.animeList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ukrdroiddev.domain.entities.AnimeDataUiEntity
import com.ukrdroiddev.presentation.R
import com.ukrdroiddev.presentation.navigation.CustomScreenBuilder
import kotlinx.collections.immutable.ImmutableList
import org.koin.androidx.compose.koinViewModel


fun CustomScreenBuilder.animeListScreen() {
    environment {
        titleRes = R.string.all_anime
    }
    content {
        val viewModel = koinViewModel<AnimeListScreenViewModel>()
        val screenState = viewModel.screenState.collectAsStateWithLifecycle()

        when (val state = screenState.value) {
            is AnimeListState.Content -> AnimeListContent(state.animeList)
            is AnimeListState.Error -> Text(text = state.error.toString())
            is AnimeListState.Loading -> Text(text = "Loading")
        }
    }
}

@Composable
fun AnimeListContent(animeList: ImmutableList<AnimeDataUiEntity>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(R.dimen.list_horizontal_padding),
            vertical = dimensionResource(R.dimen.small_padding)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding)),
    ) {
        items(animeList) { anime ->
            AnimeListItem(anime)
        }
    }
}

