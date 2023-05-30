package com.example.movies.details.data.api

import com.example.movies.details.data.api.DetailsMoviesApi.PatchConstants.DETAILS
import com.example.movies.details.data.models.DetailsMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsMoviesApi {

    @GET(DETAILS)
    suspend fun getDetailsMovies(@Path("movie_id") movie_id: Int): DetailsMoviesResponse

    object PatchConstants {
        const val DETAILS = "movie/{movie_id}"
    }
}