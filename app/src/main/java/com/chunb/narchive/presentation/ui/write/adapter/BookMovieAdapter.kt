package com.chunb.narchive.presentation.ui.write.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.databinding.ItemFormMovieBinding

class BookMovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var count = 0
    var mData = mutableListOf<Any>()

    inner class BookViewHolder(private val binding: ItemFormBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book?) {
            binding.bookData = item
        }
    }

    inner class MovieViewHolder(private val binding: ItemFormMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie?) {
            binding.movieData = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) BookViewHolder(
            ItemFormBookBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
        else MovieViewHolder(
            ItemFormMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData[position].javaClass.name.contains("Book")) {
            0
        } else if (mData[position].javaClass.name.contains("Movie")) {
            1
        } else 0
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookViewHolder -> holder.bind(mData[position] as Book)
            is MovieViewHolder -> holder.bind(mData[position] as Movie)
        }
    }
}