package com.example.movies.utils.common.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
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
            MoviesModule().load()
        }
    }
}