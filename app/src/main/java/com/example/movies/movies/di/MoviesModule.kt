package com.example.movies.movies.di

import com.example.movies.movies.data.api.MoviesApi
import com.example.movies.movies.data.datasource.MoviesDataSource
import com.example.movies.movies.data.datasource.MoviesDataSourceImpl
import com.example.movies.movies.data.repository.MoviesRepositoryImpl
import com.example.movies.movies.domain.repository.MoviesRepository
import com.example.movies.movies.domain.usecase.MoviesUseCase
import com.example.movies.movies.presentation.viewmodel.MoviesViewModel
import com.example.movies.utils.common.service.RetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MoviesModule {
    val moviesModule = module {
        factory<MoviesApi> { RetrofitService.retrofit.create(MoviesApi::class.java) }
        factory<MoviesDataSource> { MoviesDataSourceImpl(api = get()) }
        factory<MoviesRepository> { MoviesRepositoryImpl(dataSource = get()) }
        factory { MoviesUseCase(repository = get()) }
        viewModel { MoviesViewModel(useCase = get()) }
    }
}