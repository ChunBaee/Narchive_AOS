package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.presentation.util.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {

    @GET("/contents/content")
    suspend fun getContents(
        @Query("query") query : String?
    ) : Response<List<Content>>


    @GET("/contents/feed")
    suspend fun getFeed(
        @Query("page") page : Int
    ) : Response<List<Feed>>

    @POST("/contents")
    suspend fun postFeed(
        @Body body : RequestPostContent
    ) : Response<BaseResponse>

    @GET("/contents/feed/{contentId}")
    suspend fun getContentDetail(
        @Path("contentId") contentId : Int
    ) : Response<Content>
}