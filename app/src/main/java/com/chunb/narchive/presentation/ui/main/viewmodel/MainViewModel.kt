package com.chunb.narchive.presentation.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.domain.data.Contents
import com.chunb.narchive.domain.repository.ContentRepository
import com.chunb.narchive.domain.usecase.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.apache.commons.lang3.mutable.Mutable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contentUseCase: ContentUseCase,
    private val contentRepository: ContentRepository
) : ViewModel() {

    private var _homeFeedData = MutableLiveData<PagingData<ResponseFeed>>()
    val homeFeedData: LiveData<PagingData<ResponseFeed>> = _homeFeedData

    fun getFeedData() : Flow<PagingData<ResponseFeed>> {
        return contentRepository.getFeedPagingData().cachedIn(viewModelScope)
    }
}