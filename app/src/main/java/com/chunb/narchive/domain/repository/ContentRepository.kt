package com.chunb.narchive.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.ResponseFeed
import kotlinx.coroutines.flow.Flow


interface ContentRepository {

    suspend fun getContents(query : String?) : Result<List<Content>>

    suspend fun getFeed(page : Int) : Result<List<ResponseFeed>>

    fun getFeedPagingData() : Flow<PagingData<ResponseFeed>>
}