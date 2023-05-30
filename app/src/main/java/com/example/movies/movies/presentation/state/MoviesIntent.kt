package com.example.movies.movies.presentation.state

sealed class MoviesIntent {
    object FetchMovies : MoviesIntent()
}