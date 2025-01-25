package com.ukrdroiddev.domain.entities

enum class AnimeStatus(val status: String) {
    AIRING("airing"),
    COMPLETE("complete"),
    UPCOMING("upcoming");

    companion object {
        fun fromStatus(status: String): AnimeStatus? {
            return entries.find { it.status == status }
        }
    }
}