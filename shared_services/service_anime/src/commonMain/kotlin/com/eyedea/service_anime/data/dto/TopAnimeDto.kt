package com.eyedea.service_anime.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TopAnimeDto(
    val animeId: String,
    val animeImg: String,
    val animeTitle: String,
    val animeUrl: String,
    val releasedDate: String
)