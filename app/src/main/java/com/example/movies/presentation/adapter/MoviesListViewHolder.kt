package com.example.movies.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.data.models.Results
import com.example.movies.data.service.POSTER_BASE_URL
import com.example.movies.databinding.ListItemBinding

class MoviesListViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movies: Results) {
        with(binding) {
            name.text = movies.title
            date.text = movies.release_date
            Glide.with(root.context)
                .load(POSTER_BASE_URL + movies.poster_path)
                .into(imageView)
        }
    }
}