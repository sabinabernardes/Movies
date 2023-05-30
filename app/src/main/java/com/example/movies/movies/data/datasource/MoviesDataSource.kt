package com.example.movies.movies.data.datasource

import com.example.movies.movies.data.models.Movies

interface MoviesDataSource {
    suspend fun getMovies(): Movies
}