package com.chunb.narchive.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.data.remote.response.ResponseFeed
import kotlinx.coroutines.flow.Flow


interface ContentRepository {

    suspend fun getContents(query : String?) : Result<List<Content>>

    fun getFeedPagingData() : Flow<PagingData<Feed>>

    suspend fun postFeed(body : RequestPostContent) : Result<Int>

    suspend fun getDetailContent(contentId : Int) : Result<Content>
}