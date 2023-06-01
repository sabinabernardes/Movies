package com.example.movies.details.data.repository

import com.example.movies.details.DetailsMoviesHelper
import com.example.movies.details.data.datasource.DetailsMoviesDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DetailsMoviesRepositoryImplTest {

    private val dataSource = mockk<DetailsMoviesDataSource>()
    private val repository = DetailsMoviesRepositoryImpl(dataSource)

    private val movieId = 12345

    @Test
    fun `GIVEN dataSource returns movies WHEN getMovies is called THEN return movies`() =
        runBlocking {
            // GIVEN
            val expectedMovies = DetailsMoviesHelper.detailsResponse
            coEvery { dataSource.getDetailsMovies(movieId) } returns expectedMovies

            // WHEN
            val result = repository.getDetailsMovies(movieId)

            // THEN
            assertEquals(expectedMovies, result)
        }

    @Test
    fun `GIVEN dataSource throws an exception WHEN getMovies is called THEN throw an exception`() =
        runBlocking {
            // GIVEN
            val expectedException = Exception("An error occurred")
            coEvery { dataSource.getDetailsMovies(1234) } throws expectedException

            // WHEN
            val result = runCatching { dataSource.getDetailsMovies(1234) }

            // THEN
            assertTrue(result.isFailure)
            assertEquals(expectedException, result.exceptionOrNull())
        }
}