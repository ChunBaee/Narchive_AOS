package com.chunb.narchive.presentation.ui.onboarding.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivityOnboardBinding
import com.chunb.narchive.presentation.ui.main.view.MainActivity
import com.chunb.narchive.presentation.ui.onboarding.adapter.OnBoardingAdapter
import com.chunb.narchive.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnboardBinding
    private val viewModel : OnBoardingViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboard)

        initBinding()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel
    }

    fun dismissOnBoarding() {
        startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        finish()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    fun onBoardingAdapter() = OnBoardingAdapter(viewModel.onBoardingData.value as MutableList)
    fun onBoardingIndicator() = binding.beforeStartTabIndicator
    fun startButton() = binding.beforeStartBtnStart
}