package com.example.movies.movies.domain.usecase

import com.example.movies.movies.data.MoviesHelper
import com.example.movies.movies.domain.repository.MoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MoviesUseCaseTest {
    private val repository = mockk<MoviesRepository>()
    private val moviesUseCase = MoviesUseCase(repository)

    @Test
    fun `GIVEN repository returns details movies WHEN invoke is called THEN return details movies`() =
        runBlocking {
            // GIVEN
            val expectedMovies = MoviesHelper.moviesResponse
            coEvery { repository.getMovies() } returns expectedMovies

            // WHEN
            val result = moviesUseCase.invoke()

            // THEN
            assertEquals(expectedMovies, result)
        }

    @Test
    fun `GIVEN repository throws an exception WHEN invoke is called THEN throw an exception`() =
        runBlocking {
            // GIVEN
            val expectedException = Exception("An error occurred")
            coEvery { repository.getMovies() } throws expectedException

            // WHEN
            val result = runCatching { moviesUseCase.invoke() }

            // THEN
            assertTrue(result.isFailure)
            assertEquals(expectedException, result.exceptionOrNull())
        }
}
