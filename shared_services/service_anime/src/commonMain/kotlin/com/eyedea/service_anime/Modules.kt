package com.eyedea.service_anime

import com.eyedea.service_anime.data.mapper.TopRatedAnimeDtoMapper
import com.eyedea.service_anime.data.repository.ServiceAnimeRepositoryImpl
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.service_anime.domain.usecase.GetAnimeDetailUseCase
import com.eyedea.service_anime.domain.usecase.GetNewReleaseListUseCase
import com.eyedea.service_anime.domain.usecase.GetTopAiringListUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun serviceAnimeModule() = listOf(
    mapperModule(),
    repositoryModule(),
    useCaseModule()
)

private fun mapperModule() = module {
    singleOf(::TopRatedAnimeDtoMapper)
}

private fun repositoryModule() = module {
    singleOf(::ServiceAnimeRepositoryImpl){
        bind<ServiceAnimeRepository>()
        createdAtStart()
    }
}

private fun useCaseModule() = module {
    singleOf(::GetTopAiringListUseCase)
    singleOf(::GetAnimeDetailUseCase)
    singleOf(::GetNewReleaseListUseCase)
}