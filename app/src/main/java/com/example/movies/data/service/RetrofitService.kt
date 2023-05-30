package com.example.movies.data.service

import com.example.movies.data.api.MoviesApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/movie/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500/"

class RetrofitService {
    companion object {
        val service: MoviesApi

        init {
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            service = retrofit.create(MoviesApi::class.java)
        }
    }
}