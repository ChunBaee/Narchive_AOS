package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.Comment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {

    @GET("contents/comment")
    suspend fun getLastComments(
        @Query("query") query : String?
    ) : Response<List<Comment>>

}