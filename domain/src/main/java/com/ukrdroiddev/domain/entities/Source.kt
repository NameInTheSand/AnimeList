package com.ukrdroiddev.domain.entities

enum class Source(val shortName: String) {
    TV("tv"),
    MOVIE("movie"),
    OVA("ova"),
    SPECIAL("special"),
    ONA("ona"),
    MUSIC("music"),
    CM("cm"),
    PV("pv"),
    TV_SPECIAL("tv_special");

    companion object {
        fun fromType(type: String): Source? {
            return entries.find { it.shortName == type }
        }
    }
}