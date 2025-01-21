package com.ukrdroiddev.data.koinModules

import com.ukrdroiddev.data.api.RemoteApi
import org.koin.dsl.module

val dataModule = module {
    single<RemoteApi> { RemoteApi() }
    single { get<RemoteApi>().getAnimeApi() }
}