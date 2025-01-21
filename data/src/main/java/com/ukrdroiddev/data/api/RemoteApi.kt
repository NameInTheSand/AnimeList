package com.ukrdroiddev.data.api

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RemoteApi {

    fun getAnimeApi():AnimeApi {
        val ktorfit = Ktorfit.Builder().httpClient(getHttpClient()).build()
        return ktorfit.createAnimeApi()
    }

    private fun getHttpClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                requestTimeoutMillis = REQUEST_TIMEOUT
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BASE_URL
                }
            }
        }
    }

    companion object{
        private const val REQUEST_TIMEOUT = 5000L
        private const val BASE_URL = "https://api.jikan.moe/"
    }
}