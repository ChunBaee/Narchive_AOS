package com.chunb.narchive.presentation.ui.main.setting.view

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.chunb.narchive.R
import com.chunb.narchive.databinding.DialogSettingBinding
import com.chunb.narchive.presentation.ui.main.viewmodel.MainViewModel
import com.chunb.narchive.presentation.ui.splash.view.SplashActivity

class SettingDialog : DialogFragment() {
    private lateinit var binding : DialogSettingBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_setting, container, false)

        initBinding()

        return binding.root
    }

    private fun initBinding() {
        binding.dialog = this
        binding.viewModel = viewModel
    }


    override fun onResume() {
        super.onResume()
        val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.95).toInt()
        params?.height = (deviceWidth * 0.5).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    fun onClickCancel() = dismiss()

    fun onClickYes() {
        viewModel.onDialogClickYes()
        dismiss()
        startActivity(Intent(requireActivity(), SplashActivity::class.java))
        requireActivity().finish()
    }
}