package com.example.movies.data.datasource

import com.example.movies.data.api.MoviesApi
import com.example.movies.data.models.Movies

class MoviesDataSourceImpl(private val api: MoviesApi) : MoviesDataSource {
    override suspend fun getMovies(): Movies {
        return api.getMovies()
    }
}