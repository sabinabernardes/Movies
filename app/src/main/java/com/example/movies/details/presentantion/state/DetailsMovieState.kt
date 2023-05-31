package com.example.movies.details.presentantion.state

import com.example.movies.details.data.models.DetailsMoviesResponse

sealed class DetailsMovieState {
    object Inactive : DetailsMovieState()
    data class Loading(val isLoading: Boolean) : DetailsMovieState()
    data class ResponseData(val movies: DetailsMoviesResponse?) : DetailsMovieState()
    data class Error(val error: String?) : DetailsMovieState()
}