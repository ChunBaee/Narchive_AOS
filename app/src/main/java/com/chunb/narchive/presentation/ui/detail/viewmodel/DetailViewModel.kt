package com.chunb.narchive.presentation.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.domain.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val contentRepository: ContentRepository) :
    ViewModel() {

    private val _contentIdx = MutableLiveData<Int>()
    val contentIdx : LiveData<Int> = _contentIdx

    private val _contentData = MutableLiveData<Content>()
    val contentData : LiveData<Content> = _contentData

    fun setContentId(contentId : Int) {
        _contentIdx.value = contentId
    }

    fun getDetailContent(contentId: Int) {
        viewModelScope.launch {
            contentRepository.getDetailContent(contentId)
                .onSuccess { _contentData.value = it }
        }
    }
}