package com.ukrdroiddev.domain.repositories

import com.ukrdroiddev.domain.entities.AnimeDataUiEntity
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.domain.utils.Result

interface AnimeListRepository {
    suspend fun getAnimeList(): Result<List<AnimeDataUiEntity>, NetworkError>
}