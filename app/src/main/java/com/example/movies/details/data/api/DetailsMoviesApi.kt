package com.example.movies.details.data.api

import com.example.movies.details.data.api.DetailsMoviesApi.PatchConstants.DETAILS
import com.example.movies.details.data.models.DetailsMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsMoviesApi {

    @GET(DETAILS)
    suspend fun getDetailsMovies(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String,
    ): DetailsMoviesResponse

    object PatchConstants {
        const val DETAILS = "{movie_id}"
    }
}