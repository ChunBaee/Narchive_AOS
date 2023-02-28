package com.chunb.narchive.presentation.ui.filter.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.data.Mood
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.databinding.ItemFeedRvContentBinding

class FilterFeedAdapter(private val onFeedClick: (Int) -> Unit) :
    PagingDataAdapter<Feed, FilterFeedAdapter.FeedViewHolder>(diffCallback) {
    inner class FeedViewHolder(val binding: ItemFeedRvContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Feed) {
            Log.d("----", "bind: $item")
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