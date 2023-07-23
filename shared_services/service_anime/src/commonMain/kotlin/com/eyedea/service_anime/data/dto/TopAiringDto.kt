package com.eyedea.service_anime.data.dto


import kotlinx.serialization.Serializable

@Serializable
data class TopAiringDto(
    val genres: List<String>? = null,
    val id: String,
    val image: String,
    val title: String,
    val url: String,
)