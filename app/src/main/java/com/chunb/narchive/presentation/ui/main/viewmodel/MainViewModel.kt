package com.chunb.narchive.presentation.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.domain.data.Contents
import com.chunb.narchive.domain.usecase.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val contentUseCase: ContentUseCase): ViewModel() {

    private val _homeFeedData = MutableLiveData<MutableList<Contents>>()
    val homeFeedData : LiveData<MutableList<Contents>> = _homeFeedData

    fun getFeedData() {
        viewModelScope.launch {
            _homeFeedData.value = contentUseCase.mapToContents(0) as MutableList
        }
    }
}