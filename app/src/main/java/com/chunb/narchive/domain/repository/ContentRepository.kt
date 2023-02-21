package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.ResponseFeed


interface ContentRepository {

    suspend fun getContents(query : String?) : Result<List<Content>>

    suspend fun getFeed(page : Int) : Result<List<ResponseFeed>>
}