package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.service.ContentService
import com.chunb.narchive.data.source.ContentSource
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
}