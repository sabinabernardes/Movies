package com.example.movies.data.repository

import com.example.movies.data.datasource.MoviesDataSource
import com.example.movies.data.models.Movies
import com.example.movies.domain.repository.MoviesRepository
import retrofit2.Response

class MoviesRepositoryImpl(private val dataSource: MoviesDataSource) : MoviesRepository {
    override suspend fun getMovies(): Response<Movies> {
        return dataSource.getMovies()
    }
}