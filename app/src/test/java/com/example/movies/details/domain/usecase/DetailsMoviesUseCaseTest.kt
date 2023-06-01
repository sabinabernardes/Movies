package com.example.movies.details.domain.usecase

import com.example.movies.details.DetailsMoviesHelper
import com.example.movies.movies.domain.repository.DetailsMoviesRepository
import com.example.movies.movies.domain.usecase.DetailsMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DetailsMoviesUseCaseTest {

    private val repository = mockk<DetailsMoviesRepository>()
    private val detailsMoviesUseCase = DetailsMoviesUseCase(repository)

    private val movieId = 12345

    @Test
    fun `GIVEN repository returns details movies WHEN invoke is called THEN return details movies`() =
        runBlocking {
            // GIVEN
            val expectedDetailsMovies = DetailsMoviesHelper.detailsResponse
            coEvery { repository.getDetailsMovies(any()) } returns expectedDetailsMovies

            // WHEN
            val result = detailsMoviesUseCase.invoke(movieId)

            // THEN
            assertEquals(expectedDetailsMovies, result)
        }

    @Test
    fun `GIVEN repository throws an exception WHEN invoke is called THEN throw an exception`() =
        runBlocking {
            // GIVEN
            val expectedException = Exception("An error occurred")
            coEvery { repository.getDetailsMovies(any()) } throws expectedException

            // WHEN
            val result = runCatching { detailsMoviesUseCase.invoke(12345) }

            // THEN
            assertTrue(result.isFailure)
            assertEquals(expectedException, result.exceptionOrNull())
        }
}
