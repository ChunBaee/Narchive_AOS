package com.chunb.narchive.presentation.ui.write.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.databinding.ItemFormHorizontalImageBinding

class WriteImageAdapter : RecyclerView.Adapter<WriteImageAdapter.WriteImageViewHolder>() {
    var images = mutableListOf<String>()

    inner class WriteImageViewHolder(private val binding : ItemFormHorizontalImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
            binding.imagePath = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriteImageViewHolder {
        return WriteImageViewHolder(ItemFormHorizontalImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: WriteImageViewHolder, position: Int) {
        holder.bind(images[position])
    }
}