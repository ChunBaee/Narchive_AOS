package com.chunb.narchive.presentation.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.local.data.OnBoardingData
import com.chunb.narchive.databinding.ItemVpOnboardingBinding

class OnBoardingAdapter(val onBoardingItems : MutableList<OnBoardingData>) : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    inner class OnBoardingViewHolder(private val binding : ItemVpOnboardingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : OnBoardingData) {
            binding.data = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemVpOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = onBoardingItems.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }
}