package com.chunb.narchive.domain.repository

import androidx.paging.PagingData
import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.Feed
import kotlinx.coroutines.flow.Flow


interface ContentRepository {
    fun getFeedPagingData() : Flow<PagingData<Feed>>

    suspend fun postFeed(body : RequestPostContent) : Result<Int>

    suspend fun getDetailContent(contentId : Int) : Result<Content>
}