package com.example.rickandmorty.di

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

abstract class FeatureModule {

    protected open val intanceMovies: Module = module { }
    protected open val intanceDetailsMovies: Module = module { }

    protected open val additionalModule: List<Module> = emptyList()

    fun load() {
        val defaultModule = listOf(
            intanceMovies,
            intanceDetailsMovies
        )
        loadKoinModules(defaultModule + additionalModule)
    }
}