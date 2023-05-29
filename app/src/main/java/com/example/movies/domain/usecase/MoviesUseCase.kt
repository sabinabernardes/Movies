package com.example.movies.domain.usecase

import com.example.movies.data.models.Movies
import com.example.movies.domain.repository.MoviesRepository
import retrofit2.Response

class MoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(): Response<Movies> {
        return repository.getMovies()
    }
}