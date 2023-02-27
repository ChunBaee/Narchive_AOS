package com.chunb.narchive.presentation.ui.main.feed.adapter

import android.provider.MediaStore.Images
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.adapters.ViewStubBindingAdapter.setOnInflateListener
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.data.Mood
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.databinding.ItemFeedRvContentBinding
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.databinding.ItemFormMovieBinding
import com.chunb.narchive.presentation.ui.search.adapter.BookSearchAdapter

class FeedListAdapter(private val onFeedClick : (Int) -> Unit) :
    PagingDataAdapter<Feed, FeedListAdapter.FeedViewHolder>(diffCallback) {

    private var imageStrList = mutableListOf<String>()

    inner class FeedViewHolder(val binding: ItemFeedRvContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Feed) {
            Log.d("----", "bind: $item")

            binding.feed = item

//            binding.comment = if (item.comments?.isNotEmpty() == true) {
//                item.comments!![0]
//            } else {
//                Comment(0, 0, "", "", "", "")
//            }

            binding.mood = Mood.valueOf(item.mood).res

            /*binding.itemMainRvContentsBtnComment.setOnClickListener {
                commentClickedListener.commentClickedListener(it, item.content.contentIdx)
            }*/
//            var bookBinding : ItemFormBookBinding? = null
//            var movieBinding : ItemFormMovieBinding? = null
//
//            if (item.book?.isNotEmpty() == true) {
//                binding.itemMainRvContentsLayoutBook.apply {
//                    setOnInflateListener { _, _ ->
//                        bookBinding = this.binding as ItemFormBookBinding
//                        bookBinding!!.bookData = item.book[0]
//                    }
//                }
//                binding.itemMainRvContentsLayoutBook.viewStub?.inflate()
//
//            } else {
//                bookBinding?.root?.visibility = View.GONE
//                binding.itemMainRvContentsLayoutBook.viewStub?.visibility = View.GONE
//                binding.itemMainRvContentsLayoutBook.binding?.root?.visibility = View.GONE
//            }
//
//            if (item.movie?.isNotEmpty() == true) {
//                binding.itemMainRvContentsLayoutMovie.apply {
//                    setOnInflateListener { _, _ ->
//                        movieBinding = this.binding as ItemFormMovieBinding
//                        movieBinding!!.movieData = item.movie[0]
//                    }
//                }
//                binding.itemMainRvContentsLayoutMovie.viewStub?.inflate()
//
//            } else {
//                movieBinding?.root?.visibility = View.GONE
//                binding.itemMainRvContentsLayoutMovie.viewStub?.visibility = View.GONE
//                binding.itemMainRvContentsLayoutMovie.binding?.root?.visibility = View.GONE
//            }
//            lateinit var homeFeedImageAdapter : HomeFeedImageAdapter
//            if (!item.images.isNullOrEmpty()) {
//                homeFeedImageAdapter = HomeFeedImageAdapter(item.images.toMutableList())
//                binding.itemMainRvContentsRvImages.visibility = View.VISIBLE
//                binding.itemMainRvContentsRvImages.adapter = homeFeedImageAdapter
//            } else {
//                binding.itemMainRvContentsRvImages.visibility = View.GONE
//            }
//
//            binding.showBook.setOnClickListener {
//                it.isEnabled = it.isEnabled.not()
//            }
        }
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

        holder.itemView.setOnClickListener { getItem(position)?.contentIdx?.let { it1 -> onFeedClick.invoke(it1) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            ItemFeedRvContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Feed>() {

            override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem.contentIdx == newItem.contentIdx
            }

            override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem == newItem
            }
        }
    }


}