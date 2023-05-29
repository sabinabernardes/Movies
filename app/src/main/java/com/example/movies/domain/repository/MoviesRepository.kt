package com.example.movies.domain.repository

import com.example.movies.data.models.Movies
import retrofit2.Response

interface MoviesRepository {
    suspend fun getMovies(): Response<Movies>
}