package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.ResponseFeed
import com.chunb.narchive.presentation.util.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ContentService {

    @GET("/contents/content")
    suspend fun getContents(
        @Query("query") query : String?
    ) : Response<List<Content>>


    @GET("/contents")
    suspend fun getFeed(
        @Query("page") page : Int
    ) : Response<List<ResponseFeed>>

    @POST("/contents")
    suspend fun postFeed(
        @Body body : RequestPostContent
    ) : Response<BaseResponse>
}