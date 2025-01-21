package com.ukrdroiddev.data.repositories

import com.ukrdroiddev.data.dataSources.remote.AnimeListRemoteDataSource
import com.ukrdroiddev.data.mappers.AnimeRemoteToUiListMapper
import com.ukrdroiddev.domain.entities.AnimeDataUiEntity
import com.ukrdroiddev.domain.repositories.AnimeListRepository
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.domain.utils.Result

class AnimeListRepositoryImpl(
    private val remoteDataSource: AnimeListRemoteDataSource,
    private val mapper: AnimeRemoteToUiListMapper
) : AnimeListRepository {

    override suspend fun getAnimeList(): Result<List<AnimeDataUiEntity>, NetworkError> {
        return when (val remoteEntities = remoteDataSource.getAnimeList()) {
            is Result.Success -> {
                Result.Success(mapper.mapRemoteEntityListToUi(remoteEntities.data))
            }

            is Result.Error -> {
                Result.Error(remoteEntities.error)
            }
        }
    }

}