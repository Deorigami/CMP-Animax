package com.eyedea.service_anime

import com.eyedea.service_anime.data.mapper.TopAnimeDtoMapper
import com.eyedea.service_anime.data.repository.ServiceAnimeRepositoryImpl
import com.eyedea.service_anime.domain.repository.ServiceAnimeRepository
import com.eyedea.service_anime.domain.usecase.GetPopularAnimeListUseCase
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
    singleOf(::TopAnimeDtoMapper)
}

private fun repositoryModule() = module {
    singleOf(::ServiceAnimeRepositoryImpl){
        bind<ServiceAnimeRepository>()
        createdAtStart()
    }
}

private fun useCaseModule() = module {
    singleOf(::GetPopularAnimeListUseCase)
}