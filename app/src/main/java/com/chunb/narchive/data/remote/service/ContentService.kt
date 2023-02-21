package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.ResponseFeed
import retrofit2.Response
import retrofit2.http.GET
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
}