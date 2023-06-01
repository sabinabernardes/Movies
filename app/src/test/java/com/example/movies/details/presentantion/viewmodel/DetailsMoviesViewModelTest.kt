import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.details.DetailsMoviesHelper
import com.example.movies.details.presentantion.state.DetailsMovieState
import com.example.movies.details.presentantion.viewmodel.DetailsMoviesViewModel
import com.example.movies.movies.domain.usecase.DetailsMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
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

    @Test
    fun `GIVEN useCase returns details movies WHEN fetchDetailsMovies is called THEN detailsMovieState is set to ResponseData`() =
        runBlocking {
            // GIVEN
            val expectedDetailsMovies = DetailsMoviesHelper.detailsResponse
            val id = 12345
            coEvery { useCase.invoke(any()) } returns expectedDetailsMovies

            // WHEN
            viewModel.fetchDetailsMovies(id)

            // THEN
            val actualState = viewModel.detailsMovieState.value
            assertTrue(actualState is DetailsMovieState.ResponseData)
            assertEquals(
                expectedDetailsMovies,
                (actualState as DetailsMovieState.ResponseData).movies
            )
        }

    @Test
    fun `GIVEN useCase throws an exception WHEN fetchDetailsMovies is called THEN detailsMovieState is set to Error`() =
        runBlockingTest {
            // GIVEN
            val expectedException = Exception("An error occurred")
            val id = 12345
            coEvery { useCase.invoke(any()) } throws expectedException

            // WHEN
            viewModel.fetchDetailsMovies(id)

            // THEN
            val actualState = viewModel.detailsMovieState.value
            assertTrue(actualState is DetailsMovieState.Error)
            assertEquals(
                expectedException.localizedMessage,
                (actualState as DetailsMovieState.Error).error
            )
        }
}
