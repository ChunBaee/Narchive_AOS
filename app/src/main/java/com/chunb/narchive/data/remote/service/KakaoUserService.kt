package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.ResponseKakaoAuth
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface KakaoUserService {

    @GET("/v2/user/me")
    suspend fun getUserId(
        @Header("Authorization") authorization : String
    ) : Response<ResponseKakaoAuth>

}