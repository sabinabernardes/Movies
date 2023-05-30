package com.example.movies.data.datasource

import com.example.movies.data.models.Movies

interface MoviesDataSource {
    suspend fun getMovies(): Movies
}