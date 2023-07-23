package com.eyedea.service_anime.domain.entity

data class AnimeShowcaseListEntity(
    val trending : List<AnimeShowcaseEntity>,
    val popular : List<AnimeShowcaseEntity>,
    val top : List<AnimeShowcaseEntity>,
    val seasonal : List<AnimeShowcaseEntity>,
){
    companion object {
        val DEFAULT = AnimeShowcaseListEntity(trending = listOf(), popular = listOf(), top = listOf(), seasonal = listOf())
    }
}
