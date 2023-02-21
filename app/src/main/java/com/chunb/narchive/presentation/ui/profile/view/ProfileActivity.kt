package com.chunb.narchive.presentation.ui.profile.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivityProfileBinding
import com.chunb.narchive.presentation.ui.onboarding.view.OnBoardingActivity
import com.chunb.narchive.presentation.ui.profile.viewmodel.ProfileViewModel
import com.chunb.narchive.presentation.util.LoadingDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    private val viewModel : ProfileViewModel by viewModels()
    private val loadingDialog by lazy {
        LoadingDialog(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        initBinding()
        observeProfileFinished()
        observeFirebaseUploadFinish()
        observeRemoteUploadFinish()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel
    }

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        requestActivity.launch(intent)
    }

    private val requestActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data?.data != null) { //갤러리 캡쳐 결과값
                val clipData = it?.data?.clipData
                if (clipData == null) {
                    viewModel.setProfileImage(it?.data?.data!!)
                }
            }
        }

    private fun observeProfileFinished() {
        viewModel.userNickName.observe(this) {
            viewModel.setProfileFinished()
        }
        viewModel.userProfileImage.observe(this) {
            viewModel.setProfileFinished()
        }
    }

    fun uploadToFB() {
        loadingDialog.show()
        viewModel.uploadProfileToFirebase()
    }

    private fun observeFirebaseUploadFinish() {
        viewModel.userProfileImageDownloadURL.observe(this) {
            viewModel.postUserProfileToRemote(it)
        }
    }

    private fun observeRemoteUploadFinish() {
        viewModel.remoteCode.observe(this) {
            loadingDialog.dismiss()
            if(it == 200) {
                //:TODO Y/N 분기처리 하기
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()
            } else {
                Snackbar.make(binding.root, "예기치 못한 오류가 발생했습니다. 다시 시도해주세요.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}