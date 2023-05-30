package com.example.movies.movies.domain.repository

import com.example.movies.movies.data.models.Movies

interface MoviesRepository {
    suspend fun getMovies(): Movies
}