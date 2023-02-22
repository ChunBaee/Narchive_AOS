package com.chunb.narchive.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.chunb.narchive.data.remote.response.ResultSearchBook
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun kakaoBookSearch(query : String) : Flow<PagingData<ResultSearchBook>>
}