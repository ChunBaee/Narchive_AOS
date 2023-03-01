package com.chunb.narchive.presentation.ui.main.setting.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chunb.narchive.Narchive.Companion.mSharedPreferences
import com.chunb.narchive.R
import com.chunb.narchive.databinding.FragmentSettingBinding
import com.chunb.narchive.presentation.ui.main.setting.adapter.SettingMenuAdapter
import com.chunb.narchive.presentation.ui.main.viewmodel.MainViewModel
import com.chunb.narchive.presentation.ui.profile.view.ProfileActivity
import com.chunb.narchive.presentation.ui.splash.view.SplashActivity

class SettingFragment : Fragment() {
    private lateinit var binding : FragmentSettingBinding
    private val viewModel : MainViewModel by activityViewModels()

    private val settingMenuAdapter by lazy {
        SettingMenuAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)

        initBinding()
        initMenuRv()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getUserRes()
    }

    private fun initBinding() {
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initMenuRv() {
        binding.fgSettingRvMenu.adapter = settingMenuAdapter
        viewModel.settingMenuData.observe(viewLifecycleOwner) {
            settingMenuAdapter.apply {
                settingDatas = it.toMutableList()
            }
        }

        settingMenuAdapter.menuClickedListener(object : SettingMenuAdapter.MenuClickedListener {
            override fun menuClickedListener(view: View, position: Int) {
                when(position) {
                    1 -> {
                        startActivity(Intent(requireActivity(), ProfileActivity::class.java))
                    }
                    0,2 -> {
                        viewModel.setDialogType(position)
                        SettingDialog().show(childFragmentManager, "Dialog")
                    }
                }
            }
        })
    }


}