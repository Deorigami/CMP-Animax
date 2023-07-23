package com.eyedea.service_anime.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewReleaseDto(
    val episodeId: String,
    val episodeNumber: Int,
    val id: String,
    val image: String,
    val title: String,
    val url: String
)