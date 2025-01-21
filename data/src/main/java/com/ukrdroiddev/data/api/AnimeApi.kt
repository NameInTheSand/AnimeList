package com.ukrdroiddev.data.api

import com.ukrdroiddev.data.entities.responces.AnimeListResponse
import de.jensklingenberg.ktorfit.http.GET

interface AnimeApi {

    @GET("v4/anime")
    suspend fun getAnimeList():AnimeListResponse

}