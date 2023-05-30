package com.example.movies.details.presentantion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.movies.domain.usecase.DetailsMoviesUseCase
import com.example.movies.movies.presentation.state.DetailsMovieState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private const val IS_LOADING = true

class DetailsMoviesViewModel(
    private val useCase: DetailsMoviesUseCase,
) : ViewModel() {

    val detailsMovieState = MutableStateFlow<DetailsMovieState>(DetailsMovieState.Inactive)

    fun fetchDetailsMovies(id: Int) {
        viewModelScope.launch {
            detailsMovieState.value = DetailsMovieState.Loading(isLoading = IS_LOADING)
            detailsMovieState.value = try {
                DetailsMovieState.ResponseData(useCase.invoke(id = id))
            } catch (e: Exception) {
                DetailsMovieState.Error(e.localizedMessage)
            }
        }
    }
}