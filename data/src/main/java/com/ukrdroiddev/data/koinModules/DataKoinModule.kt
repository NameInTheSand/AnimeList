package com.ukrdroiddev.data.koinModules

import com.ukrdroiddev.data.api.RemoteApi
import com.ukrdroiddev.data.dataSources.remote.AnimeListRemoteDataSource
import com.ukrdroiddev.data.dataSources.remote.AnimeListRemoteDataSourceImpl
import com.ukrdroiddev.data.mappers.AnimeRemoteToUiListMapper
import com.ukrdroiddev.data.repositories.AnimeListRepositoryImpl
import com.ukrdroiddev.domain.repositories.AnimeListRepository
import org.koin.dsl.module

val dataModule = module {
    single<RemoteApi> { RemoteApi() }
    single { get<RemoteApi>().getAnimeApi() }
    single { AnimeRemoteToUiListMapper() }
    single<AnimeListRemoteDataSource> { AnimeListRemoteDataSourceImpl(get()) }
    single<AnimeListRepository> { AnimeListRepositoryImpl(get(), get()) }
}