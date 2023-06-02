import com.example.movies.movies.data.MoviesHelper
import com.example.movies.movies.data.api.MoviesApi
import com.example.movies.movies.data.datasource.MoviesDataSource
import com.example.movies.movies.data.datasource.MoviesDataSourceImpl
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesDataSourceImplTest {

    @MockK
    private var moviesApi: MoviesApi = mockk(relaxed = true)

    @MockK
    private var dataSource: MoviesDataSource =
        MoviesDataSourceImpl(moviesApi)


    @Test
    fun `GIVEN service calls datasource WHEN datasource gets a response THEN return is a success`() =
        runBlocking {

            //GIVEN
            val expectedResult = MoviesHelper.moviesResponse

            coEvery {
                moviesApi.getMovies(
                    apiKey = "bb33f04fede1d6fd48a838f399ac5b13",
                )
            }.returns(expectedResult)

            //WHEN
            val result = dataSource.getMovies()


            //THEN
            assertEquals(expectedResult, result)
        }

    @Test
    fun `GIVEN service calls datasource WHEN datasource gets a response THEN return is a error`() =
        runBlocking {

            //GIVEN
            val expectedException = Exception("An error occurred")
            coEvery {
                moviesApi.getMovies(
                    apiKey = "bb33f04fede1d6fd48a838f399ac5b13",
                )
            }.coAnswers {
                throw expectedException
            }

            //WHEN
            val result = runCatching {
                dataSource.getMovies()
            }

            //THEN
            Assert.assertTrue(result.isFailure)
            assertEquals(expectedException, result.exceptionOrNull())
        }
}

