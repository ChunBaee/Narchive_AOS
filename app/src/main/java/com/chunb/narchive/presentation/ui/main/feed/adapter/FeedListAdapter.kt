package com.chunb.narchive.presentation.ui.main.feed.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.ViewStubBindingAdapter.setOnInflateListener
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.data.Mood
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.databinding.ItemFeedRvContentBinding
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.databinding.ItemFormMovieBinding

class FeedListAdapter() : PagingDataAdapter<ResponseFeed, FeedListAdapter.FeedViewHolder>(diffCallback) {

    inner class FeedViewHolder(private val binding : ItemFeedRvContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResponseFeed) {
            binding.content = item

            binding.comment = if (item.comments?.isNotEmpty() == true) {
                item.comments!![0]
            } else {
                Comment(0, 0, "", "", "", "")
            }

            binding.mood = Mood.valueOf(item.content.mood).res


            /*binding.itemMainRvContentsBtnComment.setOnClickListener {
                commentClickedListener.commentClickedListener(it, item.content.contentIdx)
            }*/

            if (item.book?.isNotEmpty() == true) {
                binding.itemMainRvContentsLayoutBook.apply {
                    setOnInflateListener { _, _ ->
                        val bookBinding = this.binding as ItemFormBookBinding
                        bookBinding.bookData = item.book[0]
                    }
                }
                binding.itemMainRvContentsLayoutBook.viewStub?.inflate()
            }
            if (item.movie?.isNotEmpty() == true) {
                binding.itemMainRvContentsLayoutMovie.apply {
                    setOnInflateListener { _, _ ->
                        val movieBinding = this.binding as ItemFormMovieBinding
                        movieBinding.movieData = item.movie[0]
                    }
                }
                binding.itemMainRvContentsLayoutMovie.viewStub?.inflate()
            }

            Log.d("0000", "bind: ${item.images}")

            if (!item.images.isNullOrEmpty()) {
                binding.itemMainRvContentsRvImages.adapter =
                    HomeFeedImageAdapter(item.images.toMutableList())
            }
        }

    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(ItemFeedRvContentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ResponseFeed>() {

            override fun areItemsTheSame(oldItem: ResponseFeed, newItem: ResponseFeed): Boolean {
                return oldItem.content.contentIdx == newItem.content.contentIdx
            }

            override fun areContentsTheSame(oldItem: ResponseFeed, newItem: ResponseFeed): Boolean {
                return oldItem == newItem
            }
        }
    }


}