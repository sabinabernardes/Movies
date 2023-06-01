package com.example.movies.details.data.datasource

import com.example.movies.details.DetailsMoviesHelper
import com.example.movies.details.data.api.DetailsMoviesApi
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsMoviesDataSourceImplTest {
    @MockK
    private var detailsMoviesApi: DetailsMoviesApi = mockk(relaxed = true)

    @MockK
    private var detailsDataSource: DetailsMoviesDataSource =
        DetailsMoviesDataSourceImpl(detailsMoviesApi)


    @Test
    fun `GIVEN service calls datasource WHEN datasource gets a response THEN return is a success`() =
        runBlocking {

            //GIVEN
            val expectedResult = DetailsMoviesHelper.detailsResponse

            coEvery {
                detailsMoviesApi.getDetailsMovies(
                    movie_id = 12345,
                    apiKey = "bb33f04fede1d6fd48a838f399ac5b13",
                    language = "pt-BR"
                )
            }.returns(expectedResult)

            //WHEN
            val result = detailsDataSource.getDetailsMovies(12345)


            //THEN
            assertEquals(expectedResult, result)
        }

    @Test
    fun `GIVEN service calls datasource WHEN datasource gets a response THEN return is a error`() =
        runBlocking {

            //GIVEN
            val expectedException = Exception("An error occurred")
            coEvery {
                detailsMoviesApi.getDetailsMovies(
                    movie_id = 12345,
                    apiKey = "bb33f04fede1d6fd48a838f399ac5b13",
                    language = "pt-BR"
                )
            }.coAnswers {
                throw expectedException
            }

            //WHEN
            val result = runCatching {
                detailsDataSource.getDetailsMovies(12345)
            }

            //THEN
            assertTrue(result.isFailure)
            assertEquals(expectedException, result.exceptionOrNull())
        }
}