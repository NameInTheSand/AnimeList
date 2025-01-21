package com.ukrdroiddev.data.entities.responces

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeListResponse(
    val data: List<AnimeData>,
    val pagination: Pagination
)

@Serializable
data class AnimeData(
    @SerialName("mal_id") val malId: Int,
    val url: String,
    val images: Images,
    val trailer: Trailer,
    val approved: Boolean,
    val titles: List<Title>,
    val title: String,
    @SerialName("title_english") val titleEnglish: String?,
    @SerialName("title_japanese") val titleJapanese: String?,
    @SerialName("title_synonyms") val titleSynonyms: List<String>,
    val type: String,
    val source: String,
    val episodes: Int,
    val status: String,
    val airing: Boolean,
    val aired: Aired,
    val duration: String,
    val rating: String,
    val score: Float,
    @SerialName("scored_by") val scoredBy: Int,
    val rank: Int,
    val popularity: Int,
    val members: Int,
    val favorites: Int,
    val synopsis: String?,
    val background: String?,
    val season: String?,
    val year: Int?,
    val broadcast: Broadcast,
    val producers: List<Producer>,
    val licensors: List<Licensor>,
    val studios: List<Studio>,
    val genres: List<Genre>,
    @SerialName("explicit_genres") val explicitGenres: List<ExplicitGenre>,
    val themes: List<Theme>,
    val demographics: List<Demographic>
)

@Serializable
data class Images(
    val jpg: ImageUrls,
    val webp: ImageUrls
)

@Serializable
data class ImageUrls(
    @SerialName("image_url") val imageUrl: String,
    @SerialName("small_image_url") val smallImageUrl: String,
    @SerialName("large_image_url") val largeImageUrl: String
)

@Serializable
data class Trailer(
    @SerialName("youtube_id") val youtubeId: String?,
    val url: String?,
    @SerialName("embed_url") val embedUrl: String?
)

@Serializable
data class Title(
    val type: String,
    val title: String
)

@Serializable
data class Aired(
    val from: String?,
    val to: String?,
    val prop: AiredProp,
    val string: String?
)

@Serializable
data class AiredProp(
    val from: DateComponent,
    val to: DateComponent
)

@Serializable
data class DateComponent(
    val day: Int?,
    val month: Int?,
    val year: Int?
)

@Serializable
data class Broadcast(
    val day: String?,
    val time: String?,
    val timezone: String?,
    val string: String?
)

@Serializable
data class Producer(
    @SerialName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

@Serializable
data class Licensor(
    @SerialName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

@Serializable
data class Studio(
    @SerialName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

@Serializable
data class Genre(
    @SerialName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

@Serializable
data class ExplicitGenre(
    @SerialName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

@Serializable
data class Theme(
    @SerialName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

@Serializable
data class Demographic(
    @SerialName("mal_id") val malId: Int,
    val type: String,
    val name: String,
    val url: String
)

@Serializable
data class Pagination(
    @SerialName("last_visible_page") val lastVisiblePage: Int,
    @SerialName("has_next_page") val hasNextPage: Boolean,
    val items: PaginationItems
)

@Serializable
data class PaginationItems(
    val count: Int,
    val total: Int,
    @SerialName("per_page") val perPage: Int
)