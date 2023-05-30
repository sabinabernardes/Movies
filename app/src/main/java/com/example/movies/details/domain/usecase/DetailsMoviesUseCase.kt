package com.example.movies.movies.domain.usecase

import com.example.movies.details.data.models.DetailsMoviesResponse
import com.example.movies.movies.domain.repository.DetailsMoviesRepository

class DetailsMoviesUseCase(private val repository: DetailsMoviesRepository) {
    suspend operator fun invoke(id: Int): DetailsMoviesResponse {
        return repository.getDetailsMovies(id = id)
    }
}