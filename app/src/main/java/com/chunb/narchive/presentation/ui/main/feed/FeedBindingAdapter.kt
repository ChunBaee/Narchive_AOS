package com.chunb.narchive.presentation.ui.main.feed

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.presentation.ui.main.feed.adapter.FeedListAdapter


@BindingAdapter("feed_Adapter")
fun RecyclerView.setAdapter (adapter : FeedListAdapter) {
    this.adapter = adapter
}