package com.chunb.narchive.data.repository

import android.util.Log
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.source.ContentSource
import com.chunb.narchive.domain.repository.ContentRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentRemoteSource: ContentSource):
    ContentRepository {
    override suspend fun getContents(query : String?): Result<List<Content>> {
        return contentRemoteSource.getContents(query)
    }
}