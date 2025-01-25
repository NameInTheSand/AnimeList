package com.ukrdroiddev.data.mappers

import com.ukrdroiddev.data.entities.responces.AnimeListResponse
import com.ukrdroiddev.domain.entities.AnimeDataUiEntity
import com.ukrdroiddev.domain.entities.AnimeStatus
import com.ukrdroiddev.domain.entities.Source
import com.ukrdroiddev.domain.mappers.BaseRemoteResponseToUiListMapper

class AnimeRemoteToUiListMapper :
    BaseRemoteResponseToUiListMapper<AnimeListResponse, AnimeDataUiEntity> {

    override suspend fun mapRemoteEntityListToUi(response: AnimeListResponse): List<AnimeDataUiEntity> {
        return response.data.map { remoteEntity ->
            AnimeDataUiEntity(
                malId = remoteEntity.malId,
                url = remoteEntity.url,
                images = remoteEntity.images.toUiEntity(),
                trailer = remoteEntity.trailer.toUiEntity(),
                approved = remoteEntity.approved,
                title = remoteEntity.title,
                titleEnglish = remoteEntity.titleEnglish,
                titleJapanese = remoteEntity.titleJapanese,
                titleSynonyms = remoteEntity.titleSynonyms,
                type = remoteEntity.type,
                source = Source.fromType(remoteEntity.source.uppercase()),
                episodes = remoteEntity.episodes,
                status = AnimeStatus.fromStatus(remoteEntity.status) ?: AnimeStatus.UPCOMING,
                airing = remoteEntity.airing,
                duration = remoteEntity.duration,
                rating = remoteEntity.rating,
                score = remoteEntity.score,
                scoredBy = remoteEntity.scoredBy,
                rank = remoteEntity.rank,
                popularity = remoteEntity.popularity,
                members = remoteEntity.members,
                favorites = remoteEntity.favorites,
                synopsis = remoteEntity.synopsis,
                background = remoteEntity.background,
                season = remoteEntity.season,
                year = remoteEntity.year,
                producers = remoteEntity.producers.map { it.toUiEntity() },
                studios = remoteEntity.studios.map { it.toUiEntity() },
                genres = remoteEntity.genres.map { it.toUiEntity() },
                explicitGenres = remoteEntity.explicitGenres.map { it.toUiEntity() },
                themes = remoteEntity.themes.map { it.toUiEntity() },
                demographics = remoteEntity.demographics.map { it.toUiEntity() }
            )
        }
    }

}