package com.example.movies.data.datasource

import com.example.movies.BuildConfig.API_KEY
import com.example.movies.BuildConfig.TOKEN_KEY
import com.example.movies.data.api.MoviesApi
import com.example.movies.data.models.Movies

const val TOKEN_KEY = TOKEN_KEY
const val API_KEY = API_KEY

class MoviesDataSourceImpl(private val api: MoviesApi) : MoviesDataSource {
    override suspend fun getMovies(): Movies {
        return api.getMovies(apiKey = API_KEY)
    }
}