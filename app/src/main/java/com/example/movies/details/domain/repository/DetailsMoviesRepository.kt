package com.example.movies.movies.domain.repository

import com.example.movies.details.data.models.DetailsMoviesResponse

interface DetailsMoviesRepository {
    suspend fun getDetailsMovies(id: Int): DetailsMoviesResponse
}