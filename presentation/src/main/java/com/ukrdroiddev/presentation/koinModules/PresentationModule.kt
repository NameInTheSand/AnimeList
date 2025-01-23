package com.ukrdroiddev.presentation.koinModules

import com.ukrdroiddev.presentation.screens.animeList.AnimeListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { AnimeListScreenViewModel(get()) }
}