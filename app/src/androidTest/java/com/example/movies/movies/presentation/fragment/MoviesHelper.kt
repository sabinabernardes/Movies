package com.example.movies.movies.presentation.fragment

import com.example.movies.movies.data.models.Movies
import com.example.movies.movies.data.models.Results

object MoviesHelper {

    const val API_KEY = "12345"
    const val LANGUAGE = "pt-BR"
    const val MOVIE_ID = 502356

    val moviesResponse =
        Movies(
            page = 1,
            results = listOf(
                Results(
                    adult = false,
                    backdrop_path = "",
                    genre_ids = null,
                    id = 53430,
                    original_language = "en",
                    original_title = "",
                    overview = "",
                    popularity = 2.22,
                    poster_path = "",
                    release_date = "2023-03-22",
                    title = "John Wick: Chapter 4",
                    video = false,
                    vote_average = 2.22,
                    vote_count = 3
                )
            ),
            total_pages = 200,
            total_results = null
        )


}
