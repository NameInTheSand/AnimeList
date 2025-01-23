package com.ukrdroiddev.presentation.screens.animeList

import androidx.compose.material3.Text
import com.ukrdroiddev.presentation.R
import com.ukrdroiddev.presentation.navigation.CustomScreenBuilder
import org.koin.androidx.compose.koinViewModel


fun CustomScreenBuilder.animeListScreen() {
    environment {
        titleRes = R.string.all_anime
    }
    content {
        val viewModel = koinViewModel<AnimeListScreenViewModel>()
        Text("TEXT TO SHOW")
    }
}