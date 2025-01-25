package com.ukrdroiddev.domain.entities

enum class AnimeStatus(val status: String) {
    AIRING("Currently Airing"),
    FINISHED("Finished Airing"),
    UPCOMING("Upcoming");

    companion object {
        fun fromStatus(status: String): AnimeStatus? {
            return entries.find { it.status.equals(status, ignoreCase = true) }
        }
    }
}