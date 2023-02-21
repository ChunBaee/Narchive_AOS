package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.Content


interface ContentRepository {

    suspend fun getContents(query : String?) : Result<List<Content>>
}