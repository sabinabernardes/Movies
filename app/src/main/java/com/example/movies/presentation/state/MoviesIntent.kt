package com.example.movies.presentation.state

sealed class MoviesIntent {
    object FetchMovies : MoviesIntent()
}