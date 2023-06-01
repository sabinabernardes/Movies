import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movies.common.view.MainActivity
import com.example.movies.movies.presentation.fragment.MovieScreenRobot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MoviesFragmentInstrumentedTest {


    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMovieScreen() {
        val robot = MovieScreenRobot()
        robot.apply {
            verifyMovieTitle("John Wick: Chapter 4")
            verifyMovieRelease("2023-03-22")
            scrollMovieCarouselRight()
            scrollMovieCarouselLeft()
            clickMovieInCarousel(0)
        }
    }
}
