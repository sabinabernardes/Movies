package com.example.movies.movies.presentation.fragment

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.movies.R

class MovieScreenRobot {

    private val movieTitleTextView = ViewMatchers.withId(R.id.name)
    private val releaseTitleTextView = ViewMatchers.withId(R.id.date)

    fun verifyMovieTitle(expectedTitle: String) {
        Espresso.onView(movieTitleTextView)
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedTitle)))
    }

    fun verifyMovieRelease(expectedRelease: String) {
        Espresso.onView(releaseTitleTextView)
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedRelease)))
    }

    fun scrollMovieCarouselRight() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).perform(ViewActions.swipeRight())
    }

    fun scrollMovieCarouselLeft() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).perform(ViewActions.swipeLeft())
    }

    fun clickMovieInCarousel(index: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                index, ViewActions.click()
            )
        )
    }
}
