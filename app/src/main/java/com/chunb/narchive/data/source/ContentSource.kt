package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.ResponseFeed

interface ContentSource {

    suspend fun getContents(query : String?) : Result<List<Content>>

    suspend fun getFeed(page : Int) : Result<List<ResponseFeed>>
}