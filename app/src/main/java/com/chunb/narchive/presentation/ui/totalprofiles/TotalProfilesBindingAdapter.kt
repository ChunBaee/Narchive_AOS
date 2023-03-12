package com.chunb.narchive.presentation.ui.totalprofiles

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.chunb.narchive.presentation.ui.totalprofiles.adapter.UserProfileAdapter

@BindingAdapter("TotalProfile_Adapter", "TotalProfile_Divider")
fun ViewPager2.setVP(adapter : UserProfileAdapter, divider : TextView) {
    this.adapter = adapter
    this.registerOnPageChangeCallback(object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            divider.text = "${position + 1} / ${adapter.itemCount}"
        }
    })
}