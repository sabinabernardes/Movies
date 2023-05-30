package com.example.movies.movies.domain.usecase

import com.example.movies.movies.data.models.Movies
import com.example.movies.movies.domain.repository.MoviesRepository

class MoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(): Movies {
        return repository.getMovies()
    }
}