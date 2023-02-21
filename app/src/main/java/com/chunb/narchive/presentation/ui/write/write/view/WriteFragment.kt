package com.chunb.narchive.presentation.ui.write.write.view

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chunb.narchive.R
import com.chunb.narchive.databinding.FragmentWriteBinding
import com.chunb.narchive.databinding.ItemWriteSelectMoodsBinding
import com.chunb.narchive.presentation.ui.write.viewmodel.WriteViewModel
import com.chunb.narchive.presentation.ui.write.write.adapter.MoodAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteFragment : Fragment() {
    private lateinit var binding : FragmentWriteBinding
    private lateinit var popupWindow : PopupWindow
    private val popupAdapter by lazy {
        MoodAdapter()
    }

    private val viewModel : WriteViewModel by viewModels()

    private val popUpBinding by lazy {
        ItemWriteSelectMoodsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_write, container, false)

        initBinding()
        observeMoods()

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.fragment = this

    }
    
    fun initPopup() {
        viewModel.getMoodData()
        popupBinding()

        val point = Point()
        activity?.windowManager?.defaultDisplay?.getRealSize(point)

        popupWindow = PopupWindow(
            popUpBinding.root,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT, true
        )
        popupWindow.elevation = 3f
        popupWindow.showAsDropDown(binding.writeLayoutSelectMood, point.x, 0)
    }

    private fun popupBinding() {
        popUpBinding.itemWriteSelectMoodsRvMoods.adapter = popupAdapter
    }

    private fun observeMoods() {
        viewModel.moodData.observe(viewLifecycleOwner) {
            popupAdapter.moods = it
        }
    }
}