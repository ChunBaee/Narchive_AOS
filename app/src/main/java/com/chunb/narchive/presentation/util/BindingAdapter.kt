package com.chunb.narchive.presentation.util

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.chunb.narchive.R
import com.chunb.narchive.presentation.ui.onboarding.adapter.OnBoardingAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


@BindingAdapter("setCircleImage")
fun ImageView.setCircleImage(path: Any?) {
    Glide.with(this).load(path).circleCrop().into(this)
}

@BindingAdapter("setImage")
fun ImageView.setImage(path: Any?) {
    Glide.with(this).load(path).into(this)
}

@BindingAdapter("onBoardingAdapter", "setIndicator", "isEnabled")
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

@BindingAdapter("setBookAndMovieInfoForm")
fun TextView.setForm(info: String) {
    this.text = SpannableStringBuilder(info).apply {
        this.setSpan(
            ForegroundColorSpan(
                resources.getColor(R.color.color_aaaaaa)
            ), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}