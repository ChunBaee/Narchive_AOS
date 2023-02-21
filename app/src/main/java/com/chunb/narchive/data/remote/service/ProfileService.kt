package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.request.RequestPostProfile
import com.chunb.narchive.presentation.util.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileService {

    @POST("/users/profile")
    suspend fun postUserProfile(
        @Body body : RequestPostProfile
    ) : Response<BaseResponse>
}