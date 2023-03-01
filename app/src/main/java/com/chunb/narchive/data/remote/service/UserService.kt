package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/users")
    suspend fun getUserData() : Response<User>



}