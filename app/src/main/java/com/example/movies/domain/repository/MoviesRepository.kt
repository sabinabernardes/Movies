package com.example.movies.domain.repository

import com.example.movies.data.models.Movies

interface MoviesRepository {
    suspend fun getMovies(): Movies
}