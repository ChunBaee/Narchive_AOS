package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.request.RequestPostComment
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.presentation.util.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {

    @GET("contents/comment/{contentId}")
    suspend fun getComments(
        @Path("contentId") contentId : Int
    ) : Response<List<Comment>>

    @POST("contents/comment/{contentId}")
    suspend fun postComments(
        @Path("contentId") contentId : Int,
        @Body body : RequestPostComment
    ) : Response<BaseResponse>


}