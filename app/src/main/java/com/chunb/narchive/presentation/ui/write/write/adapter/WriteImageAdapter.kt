package com.chunb.narchive.presentation.ui.write.write.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.databinding.ItemFormHorizontalImageBinding

class WriteImageAdapter(private val images : MutableList<Uri>) : RecyclerView.Adapter<WriteImageAdapter.WriteImageViewHolder>() {
    //var images = mutableListOf<Uri>()

    inner class WriteImageViewHolder(private val binding : ItemFormHorizontalImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Uri) {
            Log.d("----", "bind: $item")
            binding.imagePath = item.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriteImageViewHolder {
        return WriteImageViewHolder(ItemFormHorizontalImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        Log.d("----", "getItemCount: ${images.size}")
        return images.size
    }

    override fun onBindViewHolder(holder: WriteImageViewHolder, position: Int) {
        holder.bind(images[position])
        Log.d("----", "onBindViewHolder: $images")
    }

    fun setImage(new : List<Uri>) {
        //images = new as MutableList
        notifyDataSetChanged()
    }
}