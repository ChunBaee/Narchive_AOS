package com.chunb.narchive.presentation.ui.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.ViewStubBindingAdapter.setOnInflateListener
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.data.remote.response.ResultSearchBook
import com.chunb.narchive.databinding.ItemFeedRvContentBinding
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.databinding.ItemFormMovieBinding
import com.chunb.narchive.domain.mapper.mapToBook
import com.chunb.narchive.presentation.ui.main.feed.adapter.HomeFeedImageAdapter

class BookSearchAdapter : PagingDataAdapter<ResultSearchBook, BookSearchAdapter.BookSearchViewHolder>(diffCallback) {

    inner class BookSearchViewHolder(private val binding : ItemFormBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResultSearchBook) {
            Log.d("----", "bind: $item")
            binding.bookData = item.mapToBook()
        }
    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        Log.d("----", "onBindViewHolder: ${itemCount}")
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        return BookSearchViewHolder(ItemFormBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ResultSearchBook>() {

            override fun areItemsTheSame(oldItem: ResultSearchBook, newItem: ResultSearchBook): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ResultSearchBook, newItem: ResultSearchBook): Boolean {
                return oldItem == newItem
            }
        }
    }


}