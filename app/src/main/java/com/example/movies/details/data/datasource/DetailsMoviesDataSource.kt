package com.example.movies.details.data.datasource

import com.example.movies.details.data.models.DetailsMoviesResponse

interface DetailsMoviesDataSource {
    suspend fun getDetailsMovies(id: Int): DetailsMoviesResponse
}