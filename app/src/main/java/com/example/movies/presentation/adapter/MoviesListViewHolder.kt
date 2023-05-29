package com.example.movies.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.data.models.Result
import com.example.movies.databinding.ItemMovieBinding

class MoviesListViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movies: Result) {
        with(binding) {
            textView.text = movies.title
            textView2.text = movies.release_date
            Glide.with(root.context)
                .load(movies.poster_path)
                .into(imageView)
        }
    }
}