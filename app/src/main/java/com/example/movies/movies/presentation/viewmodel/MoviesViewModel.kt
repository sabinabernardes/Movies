package com.example.movies.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.movies.domain.usecase.MoviesUseCase
import com.example.movies.movies.presentation.state.MovieState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val IS_LOADING = true

class MoviesViewModel(
    private val useCase: MoviesUseCase,
) : ViewModel() {

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