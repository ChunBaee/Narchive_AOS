package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content

interface ContentSource {
    suspend fun postFeed(body : RequestPostContent) : Result<Int>

    suspend fun getDetailContent(contentId : Int) : Result<Content>
}