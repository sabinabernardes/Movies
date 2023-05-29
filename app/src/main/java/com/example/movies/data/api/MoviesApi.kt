package com.example.movies.data.api

import com.example.movies.data.api.MoviesApi.PatchConstants.POPULAR
import com.example.movies.data.models.Movies
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET(POPULAR)
    suspend fun getMovies(): Response<Movies>

    object PatchConstants {
        const val POPULAR = "/popular"
    }
}