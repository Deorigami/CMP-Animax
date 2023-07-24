package com.eyedea.service_anime.domain.entity

data class AnimeDetailEntity(
    val id : String,
    val image : String,
    val title : String,
    val rating : String,
    val yearProduced : String,
    val genre: String,
    val episodes: List<AnimeDetailEpisodeEntity>,
    val synopsis: String
) {
    companion object {
        val DEFAULT = AnimeDetailEntity(
            id = "",
            image = "",
            title = "",
            rating = "",
            yearProduced = "",
            genre = "",
            episodes = listOf(),
            synopsis = ""
        )
    }
}

data class AnimeDetailEpisodeEntity(
    val image : String,
    val id : String,
    val title: String,
    val isWatched: Boolean = false
)
