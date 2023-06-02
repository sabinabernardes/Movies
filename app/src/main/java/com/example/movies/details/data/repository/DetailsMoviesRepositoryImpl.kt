package com.example.movies.details.data.repository

import com.example.movies.details.data.datasource.DetailsMoviesDataSource
import com.example.movies.details.data.models.DetailsMoviesResponse
import com.example.movies.details.domain.repository.DetailsMoviesRepository

class DetailsMoviesRepositoryImpl(private val dataSource: DetailsMoviesDataSource) :
    DetailsMoviesRepository {
    override suspend fun getDetailsMovies(id: Int): DetailsMoviesResponse {
        return dataSource.getDetailsMovies(id = id)
    }
}