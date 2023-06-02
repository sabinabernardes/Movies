package com.example.movies.movies.data

import com.example.movies.movies.data.models.Movies
import com.example.movies.movies.data.models.Results

object MoviesHelper {

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
                    release_date = "",
                    title = "",
                    video = false,
                    vote_average = 2.22,
                    vote_count = 3
                )
            ),
            total_pages = 200,
            total_results = null
        )


}
