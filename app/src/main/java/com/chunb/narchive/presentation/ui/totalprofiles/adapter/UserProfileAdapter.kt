package com.chunb.narchive.presentation.ui.totalprofiles.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.databinding.ItemFormFullImageBinding

class UserProfileAdapter : RecyclerView.Adapter<UserProfileAdapter.UserProfileViewHolder>() {
    var imageList = mutableListOf<String>()

    inner class UserProfileViewHolder(private val binding : ItemFormFullImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : String) {
            binding.imageUrl = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileViewHolder {
        return UserProfileViewHolder(ItemFormFullImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: UserProfileViewHolder, position: Int) {
        holder.bind(imageList[position])
    }
}