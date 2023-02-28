package com.chunb.narchive.data.remote.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.data.remote.service.ContentService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FeedPagingSource(
    private val ioDispatcher: CoroutineDispatcher,
    private val contentService: ContentService
) : PagingSource<Int, Feed>() {
    override fun getRefreshKey(state: PagingState<Int, Feed>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Feed> {
        return try {
            val page = params.key ?: 0

            val response = withContext(ioDispatcher) {
                contentService.getFeed(page)
            }

            val responseFeeds = response.body() ?: listOf()

            val prevKey = if (page == 0) null else page - 1
            val nextKey = if (responseFeeds.isEmpty()) null else page + 1

            LoadResult.Page(
                data = responseFeeds,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception : Exception) {
            return LoadResult.Error(exception)
        }
    }
}