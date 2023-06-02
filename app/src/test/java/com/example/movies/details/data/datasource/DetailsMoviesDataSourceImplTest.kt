package com.example.movies.details.data.datasource

import com.example.movies.BuildConfig.API_KEY
import com.example.movies.details.DetailsMoviesHelper
import com.example.movies.details.data.api.DetailsMoviesApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsMoviesDataSourceImplTest {

    private lateinit var detailsMoviesApi: DetailsMoviesApi
    private lateinit var detailsDataSource: DetailsMoviesDataSource

    @Before
    fun setup() {
        detailsMoviesApi = mockk(relaxed = true)
        detailsDataSource = DetailsMoviesDataSourceImpl(detailsMoviesApi)
    }

    @Test
    fun `GIVEN service calls datasource WHEN datasource gets a response THEN return is a success`() =
        runBlocking {
            // Given
            val expectedResult = DetailsMoviesHelper.detailsResponse

            coEvery {
                detailsMoviesApi.getDetailsMovies(
                    movie_id = 12345,
                    apiKey = API_KEY,
                    language = "pt-BR"
                )
            } returns expectedResult

            // When
            val result = detailsDataSource.getDetailsMovies(12345)

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `GIVEN service calls datasource WHEN datasource gets a response THEN return is a error`() =
        runBlocking {
            // Given
            val expectedException = Exception("An error occurred")
            coEvery {
                detailsMoviesApi.getDetailsMovies(
                    movie_id = 12345,
                    apiKey = API_KEY,
                    language = "pt-BR"
                )
            } coAnswers {
                throw expectedException
            }

            // When
            val result = runCatching {
                detailsDataSource.getDetailsMovies(12345)
            }

            // Then
            assertTrue(result.isFailure)
            assertEquals(expectedException, result.exceptionOrNull())
        }
}
