package com.example.movies.presentation.state

import com.example.movies.data.models.Movies

sealed class MovieState {
    object Inactive : MovieState()
    object Loading : MovieState()
    data class ResponseData(val movies: Movies?) : MovieState()
    data class Error(val error: String?) : MovieState()
}