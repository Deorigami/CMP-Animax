package com.eyedea.service_anime.domain.entity

data class TopRatedAnimeEntity(
    val animeId: Int,
    val animeImg: String,
    val animeTitle: String,
    val animeUrl: String,
    val releasedDate: String,
    val rating: String,
)