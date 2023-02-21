package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.Content

interface ContentSource {

    suspend fun getContents(query : String?) : Result<List<Content>>
}