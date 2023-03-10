package com.chunb.narchive.presentation.ui.filter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.presentation.ui.filter.adapter.FilterFeedAdapter


@BindingAdapter("filter_Adapter")
fun RecyclerView.setAdapter (adapter : FilterFeedAdapter) {
    this.adapter = adapter
}