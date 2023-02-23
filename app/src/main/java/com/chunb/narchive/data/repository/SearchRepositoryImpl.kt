package com.chunb.narchive.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.chunb.nachive.data.remote.api.search.SearchNaverService
import com.chunb.narchive.data.remote.response.ResultSearchBook
import com.chunb.narchive.data.remote.response.ResultSearchMovie
import com.chunb.narchive.data.remote.service.SearchKakaoService
import com.chunb.narchive.data.remote.source.paging.KakaoBookPagingSource
import com.chunb.narchive.data.remote.source.paging.NaverMoviePagingSource
import com.chunb.narchive.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchKakaoService: SearchKakaoService,
    private val searchNaverService : SearchNaverService
) : SearchRepository {

    override fun kakaoBookSearch(query: String): Flow<PagingData<ResultSearchBook>> {
        Log.d("----", "kakaoBookSearch: $query")
        return Pager(PagingConfig(pageSize = 10)) {
            KakaoBookPagingSource(query, Dispatchers.IO, searchKakaoService)
        }.flow
    }

    override fun naverMovieSearch(query: String): Flow<PagingData<ResultSearchMovie>> {
        return Pager(PagingConfig(pageSize = 10)) {
            NaverMoviePagingSource(query, Dispatchers.IO, searchNaverService)
        }.flow
    }
}