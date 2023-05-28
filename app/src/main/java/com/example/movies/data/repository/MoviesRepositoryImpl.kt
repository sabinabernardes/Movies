package com.example.movies.data.repository

import com.example.movies.data.datasource.MoviesDataSource
import com.example.movies.data.models.Movies
import com.example.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val dataSource: MoviesDataSource) : MoviesRepository {
    override suspend fun getMovies(): Movies {
        return dataSource.getMovies()
    }
}