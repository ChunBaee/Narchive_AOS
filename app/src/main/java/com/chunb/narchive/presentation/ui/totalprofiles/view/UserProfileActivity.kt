package com.chunb.narchive.presentation.ui.totalprofiles.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivityAllUserProfileImageBinding
import com.chunb.narchive.presentation.ui.totalprofiles.adapter.UserProfileAdapter
import com.chunb.narchive.presentation.ui.totalprofiles.viewmodel.UserProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAllUserProfileImageBinding
    private val viewModel : UserProfileViewModel by viewModels()
    private lateinit var userProfileAdapter : UserProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_user_profile_image)

        initBinding()

    }

    override fun onResume() {
        super.onResume()
        initObserve()
    }

    private fun initBinding() {
        binding.activity = this
    }

    fun adapter() = UserProfileAdapter().also { userProfileAdapter = it }
    fun divider() = binding.userProfileTvDivier

    private fun initObserve() {
        viewModel.userProfileImages.observe(this) {
            userProfileAdapter.apply {
                imageList = it
                notifyDataSetChanged()
            }
        }
    }

    fun dismiss() {
        finish()
    }
}