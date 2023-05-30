package com.example.movies.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ListItemBinding
import com.example.movies.movies.data.models.Results

class MoviesListAdapter(
    private var listMovies: List<Results> = listOf(),
    private val listener: (launch: Results) -> Unit
) : RecyclerView.Adapter<MoviesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
        holder.itemView.setOnClickListener {
            listener(movies)
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}