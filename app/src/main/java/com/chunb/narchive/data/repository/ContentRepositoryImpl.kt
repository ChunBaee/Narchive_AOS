package com.chunb.narchive.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.data.remote.service.ContentService
import com.chunb.narchive.data.remote.source.paging.FeedPagingSource
import com.chunb.narchive.data.remote.source.paging.FilteredFeedPagingSource
import com.chunb.narchive.data.source.ContentSource
import com.chunb.narchive.domain.repository.ContentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentRemoteSource: ContentSource, private val contentService: ContentService):
    ContentRepository {

    override fun getFeedPagingData(): Flow<PagingData<Feed>> {
        return Pager(PagingConfig(pageSize = 10)) {
            FeedPagingSource(Dispatchers.IO, contentService)
        }.flow
    }

    override fun getFilteredFeedPagingData(query : String): Flow<PagingData<Feed>> {
        return Pager(PagingConfig(pageSize = 10)) {
            FilteredFeedPagingSource(query, Dispatchers.IO, contentService)
        }.flow
    }

    override suspend fun postFeed(body : RequestPostContent): Result<Int> {
        return contentRemoteSource.postFeed(body)
    }

    override suspend fun getDetailContent(contentId: Int): Result<Content> {
        return contentRemoteSource.getDetailContent(contentId)
    }
}