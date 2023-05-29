package com.example.movies.data.datasource

import com.example.movies.data.api.MoviesApi
import com.example.movies.data.models.Movies
import retrofit2.Response

class MoviesDataSourceImpl(private val api: MoviesApi) : MoviesDataSource {
    override suspend fun getMovies(): Response<Movies> {
        return api.getMovies()
    }
}