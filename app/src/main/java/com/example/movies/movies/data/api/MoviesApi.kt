package com.example.movies.movies.data.api

import com.example.movies.movies.data.api.MoviesApi.PatchConstants.POPULAR
import com.example.movies.movies.data.models.Movies
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET(POPULAR)
    suspend fun getMovies(@Query("api_key") apiKey: String): Movies

    object PatchConstants {
        const val POPULAR = "popular"
    }
}