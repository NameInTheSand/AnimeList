package com.ukrdroiddev.domain.mappers

interface BaseRemoteToUiListMapper<T, V> {
    suspend fun mapRemoteEntityListToUi(remoteEntities: List<T>): List<V>
}

interface BaseRemoteResponseToUiListMapper<T, V> {
    suspend fun mapRemoteEntityListToUi(response: T): List<V>
}