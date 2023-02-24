package com.chunb.narchive.data.remote.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chunb.narchive.data.remote.service.SearchNaverService
import com.chunb.narchive.data.remote.response.ResultSearchMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NaverMoviePagingSource(
    private val query : String,
    private val ioDispatcher: CoroutineDispatcher,
    private val searchNaverService: SearchNaverService
) : PagingSource<Int, ResultSearchMovie>() {
    override fun getRefreshKey(state: PagingState<Int, ResultSearchMovie>): Int? {
        Log.d("----", "getRefreshKey: ${state.anchorPosition}")
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(10)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(10)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultSearchMovie> {
        return try {
            val page = params.key ?: 1

            val response = withContext(ioDispatcher) {
                searchNaverService.getMovieInfoData("rYIPVMa5hzDna72Bc3HG", "G7G1e_GWrz", query = query, start = page)
            }
            Log.d("----", "load: ${response.body()?.total!!} // $page")

            val responseNaver = response.body()?.items ?: listOf()

            val prevKey = if (page == 1) null else page - 10
            val nextKey = if (responseNaver.isEmpty() || response.body()?.total!! <= page) null else page + 10

            LoadResult.Page(
                data = responseNaver,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception : Exception) {
            Log.d("----", "load: ${exception.message}")
            return LoadResult.Error(exception)
        }
    }
}