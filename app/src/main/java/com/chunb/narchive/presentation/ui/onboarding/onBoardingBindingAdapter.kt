package com.chunb.narchive.presentation.ui.onboarding

import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.chunb.narchive.presentation.ui.onboarding.adapter.OnBoardingAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


@BindingAdapter("onBoard_Adapter", "onBoard_setIndicator", "onBoard_isEnabled")
fun ViewPager2.setVP(onBoardingAdapter: OnBoardingAdapter, indicator: TabLayout, startBtn: Button) {
    this.adapter = onBoardingAdapter
    this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            startBtn.isEnabled = position + 1 == onBoardingAdapter.itemCount
        }
    })
    TabLayoutMediator(indicator, this) { _, _ ->
    }.attach()
}