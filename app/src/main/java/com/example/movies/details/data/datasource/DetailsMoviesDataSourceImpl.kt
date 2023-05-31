package com.example.movies.details.data.datasource

import com.example.movies.BuildConfig.API_KEY
import com.example.movies.details.data.api.DetailsMoviesApi
import com.example.movies.details.data.models.DetailsMoviesResponse
private const val LANGUAGE = "pt-BR"

class DetailsMoviesDataSourceImpl(private val api: DetailsMoviesApi) : DetailsMoviesDataSource {
    override suspend fun getDetailsMovies(id: Int): DetailsMoviesResponse {
        return api.getDetailsMovies(
            movie_id = id,
            apiKey = API_KEY,
            language = LANGUAGE
        )
    }
}