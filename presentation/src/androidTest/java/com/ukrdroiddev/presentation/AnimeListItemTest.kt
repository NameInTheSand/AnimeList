package com.ukrdroiddev.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.ukrdroiddev.domain.entities.AiredUiEntity
import com.ukrdroiddev.domain.entities.AnimeDataUiEntity
import com.ukrdroiddev.domain.entities.AnimeImageUiEntity
import com.ukrdroiddev.domain.entities.AnimeStatus
import com.ukrdroiddev.domain.entities.FromUiEntity
import com.ukrdroiddev.domain.entities.GenreUiEntity
import com.ukrdroiddev.domain.entities.ImageUrlsUiEntity
import com.ukrdroiddev.domain.entities.PropUiEntity
import com.ukrdroiddev.domain.entities.Source
import com.ukrdroiddev.domain.entities.ToUiEntity
import com.ukrdroiddev.domain.entities.TrailerUiEntity
import com.ukrdroiddev.presentation.screens.animeList.AnimeListItem
import com.ukrdroiddev.presentation.ui.theme.MyAnimeListTheme
import org.junit.Test

class AnimeListItemTest {

    private val sampleItem = AnimeDataUiEntity(
        malId = 1,
        title = "Naruto",
        titleEnglish = "Naruto Shippuden",
        status = AnimeStatus.AIRING,
        episodes = 500,
        season = "Fall",
        year = 2020,
        score = 8.544f,
        genres = listOf(GenreUiEntity(1, "Action"), GenreUiEntity(2, "Adventure")),
        scoredBy = 50000,
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
        titleJapanese = "カウボーイビバップ",
        titleSynonyms = listOf("CB"),
        type = "TV",
        source = Source.SPECIAL,
        airing = false,
        aired = AiredUiEntity(
            from = "1998-04-03",
            to = "1999-04-24",
            prop = PropUiEntity(
                from = FromUiEntity(day = 3, month = 4, year = 1998),
                to = ToUiEntity(day = 24, month = 4, year = 1999),
                string = "Apr 3, 1998 to Apr 24, 1999"
            )
        ),
        duration = "24 min per ep",
        rating = "R - 17+",
        rank = 22,
        popularity = 22,
        members = 333,
        favorites = 50000,
        synopsis = "In the year 2071, humanity has colonized several planets...",
        background = "Background info here...",
        producers = listOf(),
        studios = listOf(),
        explicitGenres = listOf(),
        themes = listOf(),
        demographics = listOf()
    )

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun fullyDisplayedFewEpisodesFewRatings() = runComposeUiTest {
        setContent {
            MyAnimeListTheme {
                Box(Modifier.fillMaxSize()) {
                    AnimeListItem(item = sampleItem)
                }
            }
        }
        waitForIdle()
        onNodeWithText("Currently Airing", ignoreCase = true).assertIsDisplayed()
        onNodeWithText("Fall").assertIsDisplayed()
        onNodeWithText("2020").assertIsDisplayed()
        onNodeWithText("500 episodes").assertIsDisplayed()
        onNodeWithText("Naruto Shippuden").assertIsDisplayed()
        onNodeWithContentDescription("Rating").assertIsDisplayed()
        onNodeWithText("8.54").assertIsDisplayed()
        onNodeWithText("50000 users").assertIsDisplayed()
        onNodeWithText("# 22").assertIsDisplayed()
        onNodeWithText("Ranking").assertIsDisplayed()
        onNodeWithText("Action").assertIsDisplayed()
        onNodeWithText("Adventure").assertIsDisplayed()
        onNodeWithTag("loading")
            .assertExists()
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun seasonUnavailable() = runComposeUiTest {
        setContent {
            MyAnimeListTheme {
                Box(Modifier.fillMaxSize()) {
                    AnimeListItem(item = sampleItem.copy(season = null))
                }
            }
        }
        onNodeWithText("2020").assertIsDisplayed()
        onNodeWithText("Fall").assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun yearUnavailable() = runComposeUiTest {
        setContent {
            MyAnimeListTheme {
                Box(Modifier.fillMaxSize()) {
                    AnimeListItem(item = sampleItem.copy(year = null))
                }
            }
        }
        onNodeWithText("2020").assertDoesNotExist()
        onNodeWithText("Fall").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun seasonAndYearUnavailable() = runComposeUiTest {
        setContent {
            MyAnimeListTheme {
                Box(Modifier.fillMaxSize()) {
                    AnimeListItem(item = sampleItem.copy(year = null, season = null))
                }
            }
        }
        onNodeWithText("2020").assertDoesNotExist()
        onNodeWithText("Fall").assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun episodesUnavailable() = runComposeUiTest {
        setContent {
            MyAnimeListTheme {
                Box(Modifier.fillMaxSize()) {
                    AnimeListItem(item = sampleItem.copy(episodes = null))
                }
            }
        }
        onNodeWithText("500 episodes").assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun titleEnglishUnavailable() = runComposeUiTest {
        setContent {
            MyAnimeListTheme {
                Box(Modifier.fillMaxSize()) {
                    AnimeListItem(item = sampleItem.copy(titleEnglish = null))
                }
            }
        }
        onNodeWithText("Naruto").assertIsDisplayed()
        onNodeWithText("Naruto Shippuden").assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun genresUnavailable() = runComposeUiTest {
        setContent {
            MyAnimeListTheme {
                Box(Modifier.fillMaxSize()) {
                    AnimeListItem(item = sampleItem.copy(genres = emptyList()))
                }
            }
        }
        onNodeWithText("Adventure").assertDoesNotExist()
        onNodeWithText("Action").assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun checkPlurals() = runComposeUiTest {
        setContent {
            MyAnimeListTheme {
                Box(Modifier.fillMaxSize()) {
                    AnimeListItem(item = sampleItem.copy(episodes = 1, scoredBy = 1))
                }
            }
        }
        onNodeWithText("1 episode").assertIsDisplayed()
        onNodeWithText("1 user").assertIsDisplayed()
    }

}