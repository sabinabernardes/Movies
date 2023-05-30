package com.example.movies.details.presentantion

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
import com.example.movies.databinding.FragmentDetailsMoviesBinding
import com.example.movies.details.presentantion.viewmodel.DetailsMoviesViewModel
import com.example.movies.movies.presentation.state.DetailsMovieState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


public class DetailsMoviesFragment : Fragment() {
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
            viewModel.detailsMovieState.collect {
                when (it) {
                    is DetailsMovieState.Loading -> {
                        getState(isLoading = true)
                    }
                    is DetailsMovieState.ResponseData -> {
                        getState(isLoading = false)
                        binding.textView.text = it.movies?.title
                        //renderList(it.movies?.results)
                    }
                    is DetailsMovieState.Error -> {
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
        }
    }
}