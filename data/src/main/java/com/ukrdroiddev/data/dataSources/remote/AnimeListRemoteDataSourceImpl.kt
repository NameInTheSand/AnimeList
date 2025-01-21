package com.ukrdroiddev.data.dataSources.remote

import com.ukrdroiddev.data.api.AnimeApi
import com.ukrdroiddev.data.entities.responces.AnimeListResponse
import com.ukrdroiddev.data.utils.wrapNetworkExceptions
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.domain.utils.Result

class AnimeListRemoteDataSourceImpl(
    private val animeApi: AnimeApi,
) : AnimeListRemoteDataSource {

    override suspend fun getAnimeList(): Result<AnimeListResponse, NetworkError> {
        val remoteAnime = wrapNetworkExceptions {
            animeApi.getAnimeList()
        }

        return when (remoteAnime) {
            is Result.Success -> {
                Result.Success(remoteAnime.data)
            }

            is Result.Error -> {
                Result.Error(remoteAnime.error)
            }
        }
    }

}