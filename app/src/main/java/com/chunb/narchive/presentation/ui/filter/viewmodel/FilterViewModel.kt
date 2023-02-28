package com.chunb.narchive.presentation.ui.filter.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.domain.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val contentRepository: ContentRepository): ViewModel() {

    val filterInput = MutableLiveData<String>().apply { "" }

    fun findWithFilter() : Flow<PagingData<Feed>> {
        Log.d("----", "findWithFilter: ${filterInput.value}")
        return contentRepository.getFilteredFeedPagingData(filterInput.value.orEmpty()).cachedIn(viewModelScope)
    }
}