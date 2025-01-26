package com.ukrdroiddev.presentation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
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
import com.ukrdroiddev.domain.utils.NetworkError
import com.ukrdroiddev.presentation.screens.animeList.AnimeListScreenContent
import com.ukrdroiddev.presentation.screens.animeList.AnimeListState
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class AnimeListScreenTest {

    private lateinit var screenState: State<AnimeListState>
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
    private lateinit var context: Context

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun content() = runComposeUiTest {
        setContent {
            screenState = mutableStateOf(AnimeListState.Content(persistentListOf(sampleItem)))
            AnimeListScreenContent(screenState) { }
        }
        onNodeWithText("Naruto Shippuden").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun loading() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            screenState = mutableStateOf(AnimeListState.Loading)
            AnimeListScreenContent(screenState) { }
        }
        onNodeWithText(context.getString(R.string.loading)).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun error() = runComposeUiTest {
        setContent {
            context = LocalContext.current
            screenState = mutableStateOf(AnimeListState.Error(NetworkError.NO_INTERNET))
            AnimeListScreenContent(screenState) { }
        }
        onNodeWithText(context.getString(R.string.no_internet)).assertIsDisplayed()
    }

}