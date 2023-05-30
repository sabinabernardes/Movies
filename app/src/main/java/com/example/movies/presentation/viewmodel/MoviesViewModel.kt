package com.example.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.usecase.MoviesUseCase
import com.example.movies.presentation.state.MovieState
import com.example.movies.presentation.state.MoviesIntent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val IS_LOADING = true

class MoviesViewModel(
    private val useCase: MoviesUseCase,
) : ViewModel() {

    val movieIntent = Channel<MoviesIntent>(Channel.UNLIMITED)
    val movieState = MutableStateFlow<MovieState>(MovieState.Inactive)

    fun fetchMovies() {
        viewModelScope.launch {
            movieState.value = MovieState.Loading(isLoading = IS_LOADING)
            movieState.value = try {
                MovieState.ResponseData(useCase.invoke())
            } catch (e: Exception) {
                MovieState.Error(e.localizedMessage)
            }
        }
    }
}