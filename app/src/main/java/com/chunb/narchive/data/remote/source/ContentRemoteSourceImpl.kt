package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.data.remote.service.ContentService
import com.chunb.narchive.data.source.ContentSource
import com.chunb.narchive.presentation.util.BaseResponse
import javax.inject.Inject

class ContentRemoteSourceImpl @Inject constructor(private val contentService: ContentService):
    ContentSource {
    override suspend fun getContents(query : String?): Result<List<Content>> {
        val contentRes = contentService.getContents(query)
        if(contentRes.isSuccessful) {
            return Result.success((contentRes.body().orEmpty()))
        }
        return Result.failure(IllegalArgumentException())
    }

    override suspend fun postFeed(body: RequestPostContent): Result<Int> {
        val feedRes = contentService.postFeed(body)
        if (feedRes.isSuccessful) {
            return Result.success(feedRes.body()?.code!!)
        }
        return Result.failure(IllegalArgumentException())
    }

    override suspend fun getDetailContent(contentId: Int): Result<Content> {
        val contentDetailRes = contentService.getContentDetail(contentId)
        if(contentDetailRes.isSuccessful) {
            return Result.success(contentDetailRes.body()!!)
        }
        return Result.failure(IllegalArgumentException())
    }


}