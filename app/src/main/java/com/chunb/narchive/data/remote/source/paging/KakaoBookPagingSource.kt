package com.chunb.narchive.data.remote.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chunb.narchive.data.remote.response.ResultSearchBook
import com.chunb.narchive.data.remote.service.SearchKakaoService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class KakaoBookPagingSource(
    private val query : String,
    private val ioDispatcher: CoroutineDispatcher,
    private val searchKakaoService: SearchKakaoService
) : PagingSource<Int, ResultSearchBook>() {
    override fun getRefreshKey(state: PagingState<Int, ResultSearchBook>): Int? {
        Log.d("----", "getRefreshKey: ${state.anchorPosition}")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultSearchBook> {
        Log.d("----", "load: WHY?")
        return try {
            val page = params.key ?: 1

            val response = withContext(ioDispatcher) {
                searchKakaoService.getBookInfoData("KakaoAK e53c9019aeb0fc9996de090f883a002e", query = query, page = page)
            }
            Log.d("----", "load: $query")

            val responseKakao = response.body()?.documents ?: listOf()
            Log.d("----", "load: $responseKakao")

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (responseKakao.isEmpty() || response.body()?.meta?.is_end == true) null else page + 1

            LoadResult.Page(
                data = responseKakao,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception : Exception) {
            Log.d("----", "load: ${exception.message}")
            return LoadResult.Error(exception)
        }
    }
}