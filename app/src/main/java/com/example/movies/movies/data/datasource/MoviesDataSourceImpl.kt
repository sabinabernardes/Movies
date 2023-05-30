package com.example.movies.movies.data.datasource

import com.example.movies.BuildConfig.API_KEY
import com.example.movies.movies.data.api.MoviesApi
import com.example.movies.movies.data.models.Movies

class MoviesDataSourceImpl(private val api: MoviesApi) : MoviesDataSource {
    override suspend fun getMovies(): Movies {
        return api.getMovies(apiKey = API_KEY)
    }
}