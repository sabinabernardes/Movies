package com.example.movies.utils.common.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.movies.databinding.ItemMovieBinding

class MoviesItemView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ItemMovieBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun bindMovie() {

    }
}