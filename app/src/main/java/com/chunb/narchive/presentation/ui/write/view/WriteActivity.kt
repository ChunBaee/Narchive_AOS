package com.chunb.narchive.presentation.ui.write.view

import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.databinding.ActivityWriteBinding
import com.chunb.narchive.databinding.ItemWriteSelectMoodsBinding
import com.chunb.narchive.presentation.ui.search.view.SearchActivity
import com.chunb.narchive.presentation.ui.write.adapter.MoodAdapter
import com.chunb.narchive.presentation.ui.write.adapter.WriteImageAdapter
import com.chunb.narchive.presentation.ui.write.viewmodel.WriteViewModel
import com.chunb.narchive.presentation.util.getCloseAnim
import com.chunb.narchive.presentation.util.getOpenAnim
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WriteActivity : AppCompatActivity() {
    private var isDrawerOpened = false

    private lateinit var binding : ActivityWriteBinding
    private val viewModel : WriteViewModel by viewModels()

    private lateinit var popupWindow: PopupWindow
    private val popupAdapter by lazy {
        MoodAdapter()
    }
    private val writeImageAdapter by lazy {
        WriteImageAdapter()
    }

    private val popUpBinding by lazy {
        ItemWriteSelectMoodsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.chunb.narchive.R.layout.activity_write)

        initBinding()
        initImageRv()
        observeMoods()
        observeImages()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel
    }


    fun initPopup() {
        viewModel.getMoodData()
        popupBinding()

        val point = Point()
        windowManager?.defaultDisplay?.getRealSize(point)

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
                popupWindow.dismiss()
            }
        })
    }

    private fun observeMoods() {
        viewModel.moodData.observe(this) {
            popupAdapter.moods = it
        }
    }

    private fun observeImages() {
        viewModel.selectedImage.observe(this) {
            writeImageAdapter.images = it
            writeImageAdapter.notifyDataSetChanged()
        }
    }

    private fun setCurrentMood(currentMood: MoodData) {
        viewModel.setChangedMood(currentMood)
    }

    private fun initImageRv() {
        binding.writeLayoutAddedRvAddImages.adapter = writeImageAdapter
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
                    val clipDataList = mutableListOf<String>()
                    for (i in 0 until clipData.itemCount) {
                        clipDataList.add(clipData.getItemAt(i).uri.toString())
                    }
                    viewModel.setSelectedImage(clipDataList)
                }
            }
        }

    fun onAddedDrawerVisibility() {
        isDrawerOpened = isDrawerOpened.not()
        if(isDrawerOpened) {
            getOpenAnim(binding.writeLayoutAddedThings).start()
        } else {
            getCloseAnim(binding.writeLayoutAddedThings).start()
        }
    }
    fun openSearchActivity(type : Boolean) {
        val searchIntent = Intent(this, SearchActivity::class.java)
        searchIntent.putExtra("isBook", type)
        startActivity(searchIntent)
    }

}