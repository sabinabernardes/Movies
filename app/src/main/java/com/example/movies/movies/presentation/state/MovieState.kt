package com.example.movies.movies.presentation.state

import com.example.movies.movies.data.models.Movies

sealed class MovieState {
    object Inactive : MovieState()
    data class Loading(val isLoading: Boolean) : MovieState()
    data class ResponseData(val movies: Movies?) : MovieState()
    data class Error(val error: String?) : MovieState()
}