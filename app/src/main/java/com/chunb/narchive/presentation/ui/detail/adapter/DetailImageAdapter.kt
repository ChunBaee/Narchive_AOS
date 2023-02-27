package com.chunb.narchive.presentation.ui.detail.adapter

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.databinding.ItemFormFullImageBinding

class DetailImageAdapter(private val images : List<String>) : RecyclerView.Adapter<DetailImageAdapter.DetailImageViewHolder>() {

    inner class DetailImageViewHolder(private val binding : ItemFormFullImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
            binding.imageUrl = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImageViewHolder {
        return DetailImageViewHolder(ItemFormFullImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: DetailImageViewHolder, position: Int) {
        holder.bind(images[position])
    }
}