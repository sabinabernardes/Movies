package com.example.movies.utils.common.di

import com.example.rickandmorty.di.FeatureModule

internal class ListCharactersModule : FeatureModule() {

//    override val dataModule = module {
//        factory { RetrofitService.service }
//
//        factory<MoviesDataSource> {
//            MoviesDataSourceImpl(
//                api = get()
//            )
//        }
//
//        factory<MoviesRepository> {
//            MoviesRepositoryImpl(
//                dataSource = get(),
//            )
//        }
//        //factory { ListCharactersMapper() }
//    }
//
//    override val domainModule = module {
//        factory { MoviesUseCase(repository = get()) }
//    }
//
//    override val presentationModule = module {
//        viewModel {
//            MoviesViewModel(
//                useCase = get()
//            )
//        }
//    }
}