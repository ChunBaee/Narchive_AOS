package com.chunb.narchive.presentation.ui.main.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.data.Mood
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.databinding.ItemFeedRvContentBinding

class FeedListAdapter(private val onFeedClick: (Int) -> Unit) :
    PagingDataAdapter<Feed, FeedListAdapter.FeedViewHolder>(diffCallback) {
    inner class FeedViewHolder(val binding: ItemFeedRvContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Feed) {
            binding.feed = item
            binding.mood = Mood.valueOf(item.mood).res
        }
    }
    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

        holder.itemView.setOnClickListener {
            getItem(position)?.contentIdx?.let { it1 ->
                onFeedClick.invoke(
                    it1
                )
            }
        }
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