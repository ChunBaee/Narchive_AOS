package com.chunb.narchive.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.presentation.util.BaseResponse

interface ContentSource {

    suspend fun getContents(query : String?) : Result<List<Content>>
    suspend fun postFeed(body : RequestPostContent) : Result<Int>

    suspend fun getDetailContent(contentId : Int) : Result<Content>
}