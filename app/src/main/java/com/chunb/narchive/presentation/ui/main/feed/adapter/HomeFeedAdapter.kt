package com.chunb.narchive.presentation.ui.main.feed.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.adapters.ViewStubBindingAdapter.setOnInflateListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.databinding.ItemFeedRvContentBinding
import com.chunb.narchive.databinding.ItemFeedRvWhenNullBinding
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.databinding.ItemFormMovieBinding
import com.chunb.narchive.domain.data.Contents

class HomeFeedAdapter : RecyclerView.Adapter<ViewHolder>() {
    var list = mutableListOf<Contents>()

    private lateinit var commentClickedListener: CommentClickedListener

    interface CommentClickedListener {
        fun commentClickedListener(view: View, contentId: Int)
    }

    fun commentClickedListener(clickListener: CommentClickedListener) {
        this.commentClickedListener = clickListener
    }

    inner class HomeFeedWhenNullViewHolder(private val binding: ItemFeedRvWhenNullBinding) :
        ViewHolder(binding.root) {

    }

    inner class HomeFeedViewHolder(private val binding: ItemFeedRvContentBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Contents) {
            binding.content = item

            binding.itemMainRvContentsBtnComment.setOnClickListener {
                commentClickedListener.commentClickedListener(it, item.content.contentIdx)
            }

            if(item.book != null) {
                binding.itemMainRvContentsLayoutBook.apply {
                    setOnInflateListener { viewStub, view ->
                        val bookBinding = this.binding as ItemFormBookBinding
                        bookBinding.bookData = item.book
                    }
                }
                binding.itemMainRvContentsLayoutBook.viewStub?.inflate()
            }
            if(item.movie != null) {
                binding.itemMainRvContentsLayoutMovie.apply {
                    setOnInflateListener { viewStub, view ->
                        val movieBinding = this.binding as ItemFormMovieBinding
                        movieBinding.movieData = item.movie
                    }
                }
                binding.itemMainRvContentsLayoutMovie.viewStub?.inflate()
            }

            if(!item.images.isNullOrEmpty()) {
                binding.itemMainRvContentsRvImages.adapter = HomeFeedImageAdapter(item.images.toMutableList())
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return when(viewType) {
             0 -> {
                 HomeFeedViewHolder(ItemFeedRvContentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
             }
            else -> {
                HomeFeedWhenNullViewHolder(ItemFeedRvWhenNullBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is HomeFeedViewHolder -> {
                holder.bind(list[position])
                holder.setIsRecyclable(false)
            }
            is HomeFeedWhenNullViewHolder -> {}
        }
    }

    override fun getItemViewType(position: Int): Int = if(list.isEmpty()) 1 else 0

    override fun getItemCount(): Int = if (list.isEmpty()) 1 else list.size

}