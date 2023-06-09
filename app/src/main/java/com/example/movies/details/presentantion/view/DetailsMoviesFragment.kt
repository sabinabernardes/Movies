package com.example.movies.details.presentantion.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movies.common.service.POSTER_BASE_URL
import com.example.movies.databinding.FragmentDetailsMoviesBinding
import com.example.movies.details.data.models.DetailsMoviesResponse
import com.example.movies.details.presentantion.state.DetailsMovieState
import com.example.movies.details.presentantion.viewmodel.DetailsMoviesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsMoviesFragment : Fragment() {
    private lateinit var binding: FragmentDetailsMoviesBinding
    private val viewModel: DetailsMoviesViewModel by viewModel()
    private val args by navArgs<DetailsMoviesFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.fetchDetailsMovies(id = args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.detailsMovieState.collect { state ->
                when (state) {
                    is DetailsMovieState.Loading -> handleLoadingState()
                    is DetailsMovieState.ResponseData -> state.movies?.let { movies ->
                        handleResponseDataState(movies)
                    }
                    is DetailsMovieState.Error -> state.error?.let { error ->
                        handleErrorState(error)
                    }
                    else -> handleInactiveState()
                }
            }
        }
    }

    private fun handleLoadingState() {
        getState(isLoading = true)
    }

    private fun handleResponseDataState(movies: DetailsMoviesResponse) {
        getState(isLoading = false)
        bindMovies(movies)
    }

    private fun handleErrorState(error: String) {
        getState(isLoading = false)
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    private fun handleInactiveState() {
        Log.d("Inactive", "Initial state of StateFlow")
    }


    private fun bindMovies(details: DetailsMoviesResponse?) {
        binding.apply {
            title.text = details?.title
            overview.text = details?.overview
            Glide.with(root.context)
                .load(POSTER_BASE_URL + args.poster)
                .into(posterMovie)
        }
    }

    private fun getState(isLoading: Boolean) {
        binding.apply {
            progress.isVisible = isLoading
        }
    }
}