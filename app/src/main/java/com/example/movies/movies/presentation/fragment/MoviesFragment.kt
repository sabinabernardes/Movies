package com.example.movies.movies.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.FragmentMoviesBinding
import com.example.movies.movies.data.models.Results
import com.example.movies.movies.presentation.adapter.MoviesListAdapter
import com.example.movies.movies.presentation.state.MovieState
import com.example.movies.movies.presentation.viewmodel.MoviesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.fetchMovies()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.movieState.collect {
                when (it) {
                    is MovieState.Loading -> {
                        getState(isLoading = true)
                    }
                    is MovieState.ResponseData -> {
                        getState(isLoading = false)
                        renderList(it.movies?.results)
                    }
                    is MovieState.Error -> {
                        getState(isLoading = false)
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Log.d("Inactive", "Initial state of StateFlow")
                    }
                }
            }
        }
    }

    private fun getState(isLoading: Boolean) {
        binding.apply {
            progress.isVisible = isLoading
            rvMovies.isVisible = !isLoading
        }
    }

    private fun gotoDetails(results: Results) {
        val id = results.id
        val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsMoviesFragment(
            id = id
        )

        view?.findNavController()?.navigate(action)
    }

    private fun renderList(movies: List<Results>?) {
        binding.rvMovies.layoutManager = LinearLayoutManager(context)
        val adapter = movies?.let { MoviesListAdapter(it, ::gotoDetails) }
        binding.rvMovies.adapter = adapter
        adapter?.notifyDataSetChanged()
    }
}