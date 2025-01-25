package com.ukrdroiddev.presentation.screens.animeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukrdroiddev.domain.entities.AnimeDataUiEntity
import com.ukrdroiddev.domain.repositories.AnimeListRepository
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.domain.utils.Result
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListScreenViewModel(
    private val repository: AnimeListRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow<AnimeListState>(AnimeListState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _screenState.value = AnimeListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val animeList = repository.getAnimeList()) {
                is Result.Error -> _screenState.emit(AnimeListState.Error(animeList.error))
                is Result.Success -> _screenState.emit(AnimeListState.Content(animeList.data.toImmutableList()))
            }
        }
    }
}

sealed class AnimeListState {
    data class Content(val animeList: ImmutableList<AnimeDataUiEntity>) : AnimeListState()
    data class Error(val error: NetworkError) : AnimeListState()
    data object Loading : AnimeListState()
}