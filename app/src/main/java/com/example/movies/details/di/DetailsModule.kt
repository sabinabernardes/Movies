package com.example.movies.details.di

import com.example.movies.details.data.api.DetailsMoviesApi
import com.example.movies.details.data.datasource.DetailsMoviesDataSource
import com.example.movies.details.data.datasource.DetailsMoviesDataSourceImpl
import com.example.movies.details.data.repository.DetailsMoviesRepositoryImpl
import com.example.movies.details.presentantion.viewmodel.DetailsMoviesViewModel
import com.example.movies.movies.domain.repository.DetailsMoviesRepository
import com.example.movies.movies.domain.usecase.DetailsMoviesUseCase
import com.example.movies.utils.common.service.RetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DetailsModule {
    val detailsMoviesModule = module {
        factory<DetailsMoviesApi> { RetrofitService.retrofit.create(DetailsMoviesApi::class.java) }
        factory<DetailsMoviesDataSource> { DetailsMoviesDataSourceImpl(api = get()) }
        factory<DetailsMoviesRepository> { DetailsMoviesRepositoryImpl(dataSource = get()) }
        factory { DetailsMoviesUseCase(repository = get()) }
        viewModel { DetailsMoviesViewModel(useCase = get()) }
    }
}