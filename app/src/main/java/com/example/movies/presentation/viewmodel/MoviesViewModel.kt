package com.example.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.models.Movies
import com.example.movies.domain.usecase.MoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(
    private val useCase: MoviesUseCase,
    private val coroutinesDispatcherIO: CoroutineDispatcher = Dispatchers.IO,
    private val coroutinesDispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _items = MutableLiveData<Movies>()

    //observer
    val items: LiveData<Movies>
        get() = _items

    fun fetchCountries() {
        viewModelScope.launch(coroutinesDispatcherIO) {
            withContext(coroutinesDispatcherMain) {
                _items.value = useCase.invoke().body()
            }
        }
    }

}