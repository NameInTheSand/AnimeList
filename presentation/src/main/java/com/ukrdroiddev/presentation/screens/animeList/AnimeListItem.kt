package com.ukrdroiddev.presentation.screens.animeList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ukrdroiddev.domain.entities.AiredUiEntity
import com.ukrdroiddev.domain.entities.AnimeDataUiEntity
import com.ukrdroiddev.domain.entities.AnimeImageUiEntity
import com.ukrdroiddev.domain.entities.AnimeStatus
import com.ukrdroiddev.domain.entities.DemographicUIEntity
import com.ukrdroiddev.domain.entities.FromUiEntity
import com.ukrdroiddev.domain.entities.GenreUiEntity
import com.ukrdroiddev.domain.entities.ImageUrlsUiEntity
import com.ukrdroiddev.domain.entities.ProducerUiEntity
import com.ukrdroiddev.domain.entities.PropUiEntity
import com.ukrdroiddev.domain.entities.Source
import com.ukrdroiddev.domain.entities.StudioUiEntity
import com.ukrdroiddev.domain.entities.ThemeUiEntity
import com.ukrdroiddev.domain.entities.ToUiEntity
import com.ukrdroiddev.domain.entities.TrailerUiEntity
import com.ukrdroiddev.presentation.R
import com.ukrdroiddev.presentation.generics.LabelMediumText
import com.ukrdroiddev.presentation.generics.LabelSmallText
import com.ukrdroiddev.presentation.generics.TitleText
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.text.DecimalFormat

@Composable
fun AnimeListItem(item: AnimeDataUiEntity) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensionResource(R.dimen.medium_padding)))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(all = dimensionResource(R.dimen.small_padding)),
        contentAlignment = Alignment.Center
    ) {
        Row {
            AnimeLargeImage(
                item,
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.image_corner)))
                    .weight(1f)
                    .fillMaxSize()

            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_padding)))
            Column(
                modifier = Modifier.weight(2f)
            ) {
                LabelMediumText(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.status.status,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                Row {
                    Season(item, modifier = Modifier.weight(1f))
                    Episodes(item, modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                TitleText(
                    text = item.titleEnglish ?: item.title,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    UserRating(item)
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.extra_small_padding)))
                    Ranking(item)
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small_padding)))
                Genres(item.genres.toImmutableList())
            }
        }
    }
}

@Composable
private fun Episodes(item: AnimeDataUiEntity, modifier: Modifier) {
    if (item.episodes == null) return
    LabelMediumText(
        modifier = modifier,
        text = pluralStringResource(
            R.plurals.fmt_episodes,
            count = item.episodes!!,
            item.episodes!!
        )
    )
}

@Composable
private fun Season(item: AnimeDataUiEntity, modifier: Modifier) {
    if (item.season == null && item.year == null) return

    Row(modifier = modifier, horizontalArrangement = Arrangement.Start) {
        if (item.season != null) {
            LabelMediumText(
                modifier = Modifier.wrapContentWidth(),
                text = item.season!!.replaceFirstChar { it.uppercase() },
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.extra_small_padding) / 2))
        LabelMediumText(
            modifier = Modifier.wrapContentWidth(),
            text = item.year.toString(),
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.extra_small_padding)))
    }
}

@Composable
private fun RowScope.UserRating(item: AnimeDataUiEntity) {
    Column(modifier = Modifier.weight(1f)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(R.string.rating),
                modifier = Modifier.size(dimensionResource(R.dimen.medium_padding))
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.extra_small_padding) / 2))
            LabelMediumText(
                text = DecimalFormat("#.##").format(item.score)
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.extra_small_padding) / 2))
        LabelSmallText(
            text = pluralStringResource(
                R.plurals.fmt_users,
                item.scoredBy,
                item.scoredBy
            )
        )
    }
}

@Composable
private fun RowScope.Ranking(item: AnimeDataUiEntity) {
    Column(modifier = Modifier.weight(1f)) {
        LabelMediumText(
            text = stringResource(R.string.fmt_ranking, item.rank)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.extra_small_padding) / 2))
        LabelSmallText(
            text = stringResource(R.string.ranking)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Genres(genres: ImmutableList<GenreUiEntity>) {
    if (genres.isEmpty()) return
    FlowRow {
        genres.forEach {
            LabelSmallText(text = it.name)
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_padding)))
        }
    }
}

@Preview
@Composable
private fun AnimeListItemPreview() {
    AnimeListItem(
        AnimeDataUiEntity(
            malId = 1,
            url = "https://myanimelist.net/anime/1",
            images = AnimeImageUiEntity(
                jpg = ImageUrlsUiEntity(
                    imageUrl = "https://cdn.myanimelist.net/images/anime/4/19644.jpg",
                    smallImageUrl = "https://cdn.myanimelist.net/images/anime/4/19644.jpg",
                    largeImageUrl = "https://cdn.myanimelist.net/images/anime/4/19644.jpg"
                ),
                webp = ImageUrlsUiEntity(
                    imageUrl = "https://cdn.myanimelist.net/images/anime/1.jpg",
                    smallImageUrl = "https://cdn.myanimelist.net/images/anime/1_small.jpg",
                    largeImageUrl = "https://cdn.myanimelist.net/images/anime/1_large.jpg"
                )

            ),
            trailer = TrailerUiEntity(
                youtubeId = "abcd1234",
                url = "https://youtube.com/watch?v=abcd1234",
                embedUrl = "https://www.youtube.com/embed/abcd1234"
            ),
            approved = true,
            title = "Cowboy Bebop",
            titleEnglish = "Cowboy Bebop",
            titleJapanese = "カウボーイビバップ",
            titleSynonyms = listOf("CB"),
            type = "TV",
            source = Source.SPECIAL,
            episodes = 26,
            status = AnimeStatus.AIRING,
            airing = false,
            duration = "24 min per ep",
            rating = "R - 17+",
            score = 8.9f,
            scoredBy = 123456,
            rank = 1,
            popularity = 5,
            members = 2000000,
            favorites = 50000,
            synopsis = "In the year 2071, humanity has colonized several planets...",
            background = "Background info here...",
            season = "spring",
            year = 1998,
            producers = listOf(
                ProducerUiEntity(
                    malId = 1,
                    name = "Sunrise",
                )
            ),
            studios = listOf(
                StudioUiEntity(
                    malId = 1,
                    type = "Studio",
                    name = "Sunrise",
                    url = "https://myanimelist.net/studio/1"
                )
            ),
            genres = listOf(
                GenreUiEntity(malId = 1, name = "Action")
            ),
            explicitGenres = listOf(),
            themes = listOf(
                ThemeUiEntity(malId = 1, name = "Space")
            ),
            demographics = listOf(
                DemographicUIEntity(
                    malId = 1,
                    name = "Seinen"
                )
            ),
            aired = AiredUiEntity(
                from = "1998-04-03",
                to = "1999-04-24",
                prop = PropUiEntity(
                    from = FromUiEntity(day = 3, month = 4, year = 1998),
                    to = ToUiEntity(day = 24, month = 4, year = 1999),
                    string = "Apr 3, 1998 to Apr 24, 1999"
                )
            )
        )
    )
}