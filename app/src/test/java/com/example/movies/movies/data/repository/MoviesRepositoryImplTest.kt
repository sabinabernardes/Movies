package com.example.movies.movies.data.repository

import com.example.movies.movies.data.MoviesHelper
import com.example.movies.movies.data.datasource.MoviesDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MoviesRepositoryImplTest {

    private val dataSource = mockk<MoviesDataSource>()
    private val repository = MoviesRepositoryImpl(dataSource)

    @Test
    fun `GIVEN dataSource returns movies WHEN dataSource is called THEN return movies`() =
        runBlocking {
            // GIVEN
            val expectedMovies = MoviesHelper.moviesResponse
            coEvery { dataSource.getMovies() } returns expectedMovies

            // WHEN
            val result = repository.getMovies()

            // THEN
            assertEquals(expectedMovies, result)
        }

    @Test
    fun `GIVEN dataSource throws an exception WHEN dataSource is called THEN throw an exception`() =
        runBlocking {
            // GIVEN
            val expectedException = Exception("An error occurred")
            coEvery { dataSource.getMovies() } throws expectedException

            // WHEN
            val result = runCatching { dataSource.getMovies() }

            // THEN
            assertTrue(result.isFailure)
            assertEquals(expectedException, result.exceptionOrNull())
        }
}
