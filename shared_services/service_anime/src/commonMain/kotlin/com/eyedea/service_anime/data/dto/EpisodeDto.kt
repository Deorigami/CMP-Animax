package com.eyedea.service_anime.data.dto


import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    val id: String,
    val number: Int,
    val url: String
)