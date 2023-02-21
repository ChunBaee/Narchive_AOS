package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.request.RequestPostSignIn
import com.chunb.narchive.data.remote.response.ResponseSignIn
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/users/auth/sign-in")
    suspend fun postSignIn(
        @Body body: RequestPostSignIn
    ) : Response<ResponseSignIn>

    @POST("/users/auth")
    suspend fun autoSignIn() : Response<ResponseSignIn>
}