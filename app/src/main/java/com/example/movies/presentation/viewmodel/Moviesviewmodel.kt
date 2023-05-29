package com.example.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.models.Movies
import com.example.movies.domain.usecase.MoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Moviesviewmodel(private val useCase: MoviesUseCase) : ViewModel() {

    private val _items = MutableLiveData<Movies>()
//observer
    val items: LiveData<Movies>
        get() = _items

    fun fetchCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _items.value = useCase.invoke().body()
            }
        }
    }

}