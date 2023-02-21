package com.chunb.narchive.presentation.ui.main.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.databinding.ItemFormHorizontalImageBinding

class HomeFeedImageAdapter(private val images : MutableList<String>) : RecyclerView.Adapter<HomeFeedImageAdapter.HomeFeedImageViewHolder>() {

    inner class HomeFeedImageViewHolder(private val binding : ItemFormHorizontalImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
            binding.imagePath = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFeedImageViewHolder {
        return HomeFeedImageViewHolder(ItemFormHorizontalImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: HomeFeedImageViewHolder, position: Int) {
        holder.bind(images[position])
    }
}