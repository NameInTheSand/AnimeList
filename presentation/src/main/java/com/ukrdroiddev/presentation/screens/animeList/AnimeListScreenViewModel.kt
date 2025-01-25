package com.ukrdroiddev.presentation.screens.animeList

import androidx.lifecycle.ViewModel
import com.ukrdroiddev.domain.repositories.AnimeListRepository

class AnimeListScreenViewModel(
    val repository: AnimeListRepository
) : ViewModel() {
}