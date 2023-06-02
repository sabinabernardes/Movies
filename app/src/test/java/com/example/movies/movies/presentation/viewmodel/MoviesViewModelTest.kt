package com.example.movies.movies.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movies.movies.data.MoviesHelper
import com.example.movies.movies.domain.usecase.MoviesUseCase
import com.example.movies.movies.presentation.state.MovieState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel

    @MockK
    private lateinit var useCase: MoviesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MoviesViewModel(useCase)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN useCase returns details movies WHEN viewModel is called THEN detailsMovieState is set to ResponseData`() =
        runBlocking {
            // GIVEN
            val expectedMovies = MoviesHelper.moviesResponse
            val id = 12345
            coEvery { useCase.invoke() } returns expectedMovies

            // WHEN
            viewModel.fetchMovies()

            // THEN
            val actualState = viewModel.movieState.value
            assertTrue(actualState is MovieState.ResponseData)
            TestCase.assertEquals(expectedMovies, (actualState as MovieState.ResponseData).movies)
        }

    @Test
    fun `GIVEN useCase throws an exception WHEN viewModel is called THEN detailsMovieState is set to Error`() =
        runBlocking {
            // GIVEN
            val expectedException = Exception("An error occurred")
            val id = 12345
            coEvery { useCase.invoke() } throws expectedException

            // WHEN
            viewModel.fetchMovies()

            // THEN
            val actualState = viewModel.movieState.value
            assertTrue(actualState is MovieState.Error)
            TestCase.assertEquals(
                expectedException.localizedMessage,
                (actualState as MovieState.Error).error
            )
        }
}
