package com.example.movies.di

import com.example.movies.data.datasource.MoviesDataSource
import com.example.movies.data.datasource.MoviesDataSourceImpl
import com.example.movies.data.repository.MoviesRepositoryImpl
import com.example.movies.data.service.RetrofitService
import com.example.movies.domain.repository.MoviesRepository
import com.example.movies.domain.usecase.MoviesUseCase
import com.example.movies.presentation.viewmodel.MoviesViewModel
import com.example.rickandmorty.di.FeatureModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal class ListCharactersModule : FeatureModule() {

    override val dataModule = module {
        factory { RetrofitService.service }

        factory<MoviesDataSource> {
            MoviesDataSourceImpl(
                api = get()
            )
        }

        factory<MoviesRepository> {
            MoviesRepositoryImpl(
                dataSource = get(),
            )
        }
        //factory { ListCharactersMapper() }
    }

    override val domainModule = module {
        factory { MoviesUseCase(repository = get()) }
    }

    override val presentationModule = module {
        viewModel {
            MoviesViewModel(
                useCase = get()
            )
        }
    }
}