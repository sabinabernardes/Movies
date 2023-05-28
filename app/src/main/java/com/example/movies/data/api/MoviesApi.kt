package com.example.movies.data.api

import com.example.movies.data.models.Movies
import retrofit2.http.GET

interface MoviesApi {

    @GET("/endpoint")
    suspend fun getMovies(): Movies

}