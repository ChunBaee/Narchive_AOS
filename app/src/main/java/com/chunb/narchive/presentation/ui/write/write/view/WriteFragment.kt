package com.chunb.narchive.presentation.ui.write.write.view

import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chunb.narchive.R
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.databinding.FragmentWriteBinding
import com.chunb.narchive.databinding.ItemWriteSelectMoodsBinding
import com.chunb.narchive.presentation.ui.write.viewmodel.WriteViewModel
import com.chunb.narchive.presentation.ui.write.write.adapter.MoodAdapter
import com.chunb.narchive.presentation.ui.write.write.adapter.WriteImageAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteFragment : Fragment() {
    private lateinit var binding: FragmentWriteBinding
    private lateinit var popupWindow: PopupWindow
    private val popupAdapter by lazy {
        MoodAdapter()
    }

    private val viewModel: WriteViewModel by viewModels()

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
        initWriteImageRV()
        observeImages()

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this
        binding.viewModel = viewModel
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

        popupAdapter.moodClickedListener(object : MoodAdapter.MoodClickedListener {
            override fun moodClickedListener(view: View, position: Int) {
                setCurrentMood(popupAdapter.moods[position])
            }
        })
    }

    private fun observeMoods() {
        viewModel.moodData.observe(viewLifecycleOwner) {
            popupAdapter.moods = it
        }
    }
    private fun observeImages() {
        viewModel.selectedImage.observe(viewLifecycleOwner) {
            Log.d("----", "observeImages: $it")
            binding.writeLayoutAddedRvAddImages.adapter = WriteImageAdapter(it)
        }
    }

    fun openAddedDrawer() {
        if(binding.writeLayoutAddedThings.visibility == View.VISIBLE) {
            binding.writeLayoutAddedThings.visibility = View.GONE
        } else {
            binding.writeLayoutAddedThings.visibility = View.VISIBLE
        }


//        binding.writeLayoutAddedThings.visibility.apply {
//            if(this == View.VISIBLE) View.GONE else View.VISIBLE
//        }
    }

    private fun setCurrentMood(currentMood: MoodData) {
        viewModel.setChangedMood(currentMood)
    }

    private fun initWriteImageRV() {
        //binding.writeLayoutAddedRvAddImages.adapter = writeImageAdapter
    }

    fun initOpenGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_PICK
        requestActivity.launch(intent)
    }

    private val requestActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data?.clipData != null) {
                val clipData = it.data?.clipData!!
                val maxClipDataSize = 4
                if(clipData.itemCount > maxClipDataSize) {
                    Snackbar.make(binding.root, "이미지는 4장까지 선택할 수 있어요!", Snackbar.LENGTH_SHORT).show()
                } else {
                    val clipDataList = mutableListOf<Uri>()
                    for (i in 0 until clipData.itemCount) {
                        clipDataList.add(clipData.getItemAt(i).uri)
                    }
                    viewModel.setSelectedImage(clipDataList)
                }
            }
        }
}