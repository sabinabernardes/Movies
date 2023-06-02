package com.example.movies.details.domain.usecase

import com.example.movies.details.data.models.DetailsMoviesResponse
import com.example.movies.details.domain.repository.DetailsMoviesRepository

class DetailsMoviesUseCase(private val repository: DetailsMoviesRepository) {
    suspend operator fun invoke(id: Int): DetailsMoviesResponse {
        return repository.getDetailsMovies(id = id)
    }
}