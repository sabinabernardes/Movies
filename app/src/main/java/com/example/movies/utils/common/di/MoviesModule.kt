package com.example.movies.utils.common.di

import com.example.movies.details.data.api.DetailsMoviesApi
import com.example.movies.details.data.datasource.DetailsMoviesDataSource
import com.example.movies.details.data.datasource.DetailsMoviesDataSourceImpl
import com.example.movies.details.data.repository.DetailsMoviesRepositoryImpl
import com.example.movies.details.presentantion.viewmodel.DetailsMoviesViewModel
import com.example.movies.movies.data.api.MoviesApi
import com.example.movies.movies.data.datasource.MoviesDataSource
import com.example.movies.movies.data.datasource.MoviesDataSourceImpl
import com.example.movies.movies.data.repository.MoviesRepositoryImpl
import com.example.movies.movies.domain.repository.DetailsMoviesRepository
import com.example.movies.movies.domain.repository.MoviesRepository
import com.example.movies.movies.domain.usecase.DetailsMoviesUseCase
import com.example.movies.movies.domain.usecase.MoviesUseCase
import com.example.movies.movies.presentation.viewmodel.MoviesViewModel
import com.example.movies.utils.common.service.RetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal class MoviesModule : FeatureModule() {
    override val dataModule = module {
        factory<MoviesApi> { RetrofitService.retrofit.create(MoviesApi::class.java) }
        factory<DetailsMoviesApi> { RetrofitService.retrofit.create(DetailsMoviesApi::class.java) }
        factory<MoviesDataSource> { MoviesDataSourceImpl(api = get()) }
        factory<DetailsMoviesDataSource> { DetailsMoviesDataSourceImpl(api = get()) }
        factory<MoviesRepository> {
            MoviesRepositoryImpl(
                dataSource = get(),
            )
        }
        factory<DetailsMoviesRepository> {
            DetailsMoviesRepositoryImpl(
                dataSource = get(),
            )
        }
//        factory { MoviesMapper() }
//        factory { UpcomingMapper() }
    }
    override val domainModule = module {
        factory { MoviesUseCase(repository = get()) }
        factory { DetailsMoviesUseCase(repository = get()) }
    }
    override val presentationModule =
        module {
            viewModel { MoviesViewModel(useCase = get()) }
            viewModel {
                DetailsMoviesViewModel(useCase = get())
            }
        }
}