package com.example.movies.data.datasource

import com.example.movies.data.models.Movies
import retrofit2.Response

interface MoviesDataSource {
    suspend fun getMovies(): Response<Movies>
}