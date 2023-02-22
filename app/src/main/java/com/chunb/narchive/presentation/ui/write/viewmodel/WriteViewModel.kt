package com.chunb.narchive.presentation.ui.write.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.R
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.domain.repository.LocalResourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(private val localResourceRepository: LocalResourceRepository): ViewModel() {
    private val _moodData = MutableLiveData<MutableList<MoodData>>()
    val moodData : LiveData<MutableList<MoodData>> = _moodData

    val currentMood = MutableLiveData<MoodData>(MoodData(Pair("normal", R.drawable.ic_face_default)))

    private val _selectedImage = MutableLiveData<MutableList<String>>()
    val selectedImage : LiveData<MutableList<String>> = _selectedImage
    fun getMoodData() {
        viewModelScope.launch {
            _moodData.value = localResourceRepository.getMoodsData() as MutableList
        }
    }

    fun setChangedMood(selectedMood : MoodData) {
        currentMood.value = selectedMood
    }

    fun setSelectedImage(selectedImageList : List<String>) {
        _selectedImage.value = selectedImageList as MutableList
    }
}