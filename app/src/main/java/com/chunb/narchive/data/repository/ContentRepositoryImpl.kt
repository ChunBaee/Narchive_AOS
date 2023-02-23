package com.chunb.narchive.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.data.remote.service.ContentService
import com.chunb.narchive.data.remote.source.paging.FeedPagingSource
import com.chunb.narchive.data.source.ContentSource
import com.chunb.narchive.domain.repository.ContentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentRemoteSource: ContentSource, private val contentService: ContentService):
    ContentRepository {
    override suspend fun getContents(query : String?): Result<List<Content>> {
        return contentRemoteSource.getContents(query)
    }

    override suspend fun getFeed(page: Int): Result<List<ResponseFeed>> {
        return contentRemoteSource.getFeed(page)
    }

    override fun getFeedPagingData(): Flow<PagingData<ResponseFeed>> {
        return Pager(PagingConfig(pageSize = 10)) {
            FeedPagingSource(Dispatchers.IO, contentService)
        }.flow
    }

    override suspend fun postFeed(body : RequestPostContent): Result<Int> {
        return contentRemoteSource.postFeed(body)
    }
}