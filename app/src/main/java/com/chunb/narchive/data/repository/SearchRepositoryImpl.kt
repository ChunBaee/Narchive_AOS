package com.chunb.narchive.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.chunb.narchive.data.remote.response.ResultSearchBook
import com.chunb.narchive.data.remote.service.SearchKakaoService
import com.chunb.narchive.data.remote.source.paging.KakaoBookPagingSource
import com.chunb.narchive.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchKakaoService: SearchKakaoService,
) : SearchRepository {

    override fun kakaoBookSearch(query: String): Flow<PagingData<ResultSearchBook>> {
        Log.d("----", "kakaoBookSearch: $query")
        return Pager(PagingConfig(pageSize = 10)) {
            KakaoBookPagingSource(query, Dispatchers.IO, searchKakaoService)
        }.flow
    }
}