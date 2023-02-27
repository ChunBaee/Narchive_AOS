package com.chunb.narchive.presentation.ui.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.databinding.ItemCommentRvCommentBinding
import com.chunb.narchive.databinding.ItemCommentRvWhenNullBinding

class CommentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var commentList = mutableListOf<Comment>()

    inner class CommentViewHolder(val binding : ItemCommentRvCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Comment) {
            binding.data = item
        }
    }

    inner class  CommentNullViewHolder(val binding : ItemCommentRvWhenNullBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0) {
            CommentViewHolder(
                ItemCommentRvCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            CommentNullViewHolder(ItemCommentRvWhenNullBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int) : Int = if (commentList.isEmpty()) 1 else 0

    override fun getItemCount(): Int = if (commentList.isEmpty()) 1 else commentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(holder) {
            is CommentViewHolder -> holder.bind(commentList[position])
        }
    }
}