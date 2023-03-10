package com.chunb.narchive.presentation.ui.comment

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.presentation.ui.comment.adapter.CommentAdapter

@BindingAdapter("comment_Adapter")
fun RecyclerView.setAdapter(adapter : CommentAdapter) {
    this.adapter = adapter
}