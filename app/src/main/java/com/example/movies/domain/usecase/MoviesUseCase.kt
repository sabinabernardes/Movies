package com.example.movies.domain.usecase

import com.example.movies.data.models.Movies
import com.example.movies.domain.repository.MoviesRepository

class MoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(): Movies {
        return repository.getMovies()
    }
}