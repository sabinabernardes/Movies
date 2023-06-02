import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.details.DetailsMoviesHelper
import com.example.movies.details.domain.usecase.DetailsMoviesUseCase
import com.example.movies.details.presentantion.state.DetailsMovieState
import com.example.movies.details.presentantion.viewmodel.DetailsMoviesViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
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
class DetailsMoviesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var viewModel: DetailsMoviesViewModel

    @MockK
    private lateinit var useCase: DetailsMoviesUseCase

    @MockK(relaxed = true)
    private lateinit var observer: Observer<DetailsMovieState>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = DetailsMoviesViewModel(useCase)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    private val movieId = 12345

    @Test
    fun `GIVEN useCase returns details movies WHEN viewModel is called THEN detailsMovieState is set to ResponseData`() =
        runBlocking {
            // GIVEN
            val expectedDetailsMovies = DetailsMoviesHelper.detailsResponse
            coEvery { useCase.invoke(any()) } returns expectedDetailsMovies

            // WHEN
            viewModel.fetchDetailsMovies(movieId)

            // THEN
            val actualState = viewModel.detailsMovieState.value
            assertTrue(actualState is DetailsMovieState.ResponseData)
            assertEquals(
                expectedDetailsMovies,
                (actualState as DetailsMovieState.ResponseData).movies
            )
        }

    @Test
    fun `GIVEN useCase throws an exception WHEN viewModel is called THEN detailsMovieState is set to Error`() =
        runBlocking {
            // GIVEN
            val expectedException = Exception("An error occurred")
            coEvery { useCase.invoke(any()) } throws expectedException

            // WHEN
            viewModel.fetchDetailsMovies(movieId)

            // THEN
            val actualState = viewModel.detailsMovieState.value
            assertTrue(actualState is DetailsMovieState.Error)
            assertEquals(
                expectedException.localizedMessage,
                (actualState as DetailsMovieState.Error).error
            )
        }
}
