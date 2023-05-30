package com.example.movies.movies.data.repository

import com.example.movies.movies.data.datasource.MoviesDataSource
import com.example.movies.movies.data.models.Movies
import com.example.movies.movies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val dataSource: MoviesDataSource) : MoviesRepository {
    override suspend fun getMovies(): Movies {
        return dataSource.getMovies()
    }
}