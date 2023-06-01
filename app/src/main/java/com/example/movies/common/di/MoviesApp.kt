package com.example.movies.common.di

import android.app.Application
import com.example.movies.details.di.DetailsModule.detailsMoviesModule
import com.example.movies.movies.di.MoviesModule.moviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(appDeclaration = KoinAppDeclarationProvider.get(this))
    }

    object KoinAppDeclarationProvider {

        fun get(application: Application): KoinAppDeclaration = {
            androidContext(application)
            loadKoinModules(moviesModule)
            loadKoinModules(detailsMoviesModule)
        }
    }
}