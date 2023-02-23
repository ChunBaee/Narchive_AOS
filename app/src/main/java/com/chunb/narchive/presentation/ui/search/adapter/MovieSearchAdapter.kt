package com.chunb.narchive.presentation.ui.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.remote.response.ResultSearchBook
import com.chunb.narchive.data.remote.response.ResultSearchMovie
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.databinding.ItemFormMovieBinding
import com.chunb.narchive.domain.mapper.mapToBook
import com.chunb.narchive.domain.mapper.mapToMovie

class MovieSearchAdapter : PagingDataAdapter<ResultSearchMovie, MovieSearchAdapter.MovieSearchViewHolder>(diffCallback) {

    inner class MovieSearchViewHolder(private val binding : ItemFormMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResultSearchMovie) {
            Log.d("----", "bind: $item")
            binding.movieData = item.mapToMovie()
        }
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        return MovieSearchViewHolder(ItemFormMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ResultSearchMovie>() {

            override fun areItemsTheSame(oldItem: ResultSearchMovie, newItem: ResultSearchMovie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ResultSearchMovie, newItem: ResultSearchMovie): Boolean {
                return oldItem == newItem
            }
        }
    }


}