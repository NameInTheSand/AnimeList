package com.ukrdroiddev.data.dataSources.remote

import com.ukrdroiddev.data.entities.responces.AnimeListResponse
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.domain.utils.Result

interface AnimeListRemoteDataSource {
    suspend fun getAnimeList(): Result<AnimeListResponse, NetworkError>
}