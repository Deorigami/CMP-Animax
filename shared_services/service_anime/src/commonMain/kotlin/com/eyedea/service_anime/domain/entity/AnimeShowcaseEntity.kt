package com.eyedea.service_anime.domain.entity

data class AnimeShowcaseEntity(
    val animeId: String,
    val animeImg: String,
    val animeTitle: String,
    val animeUrl: String,
    val releasedDate: String,
    val rating: String,
    val genres : String
)