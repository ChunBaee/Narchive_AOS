package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("/users/contents")
    suspend fun getUserWithContentId(
        @Query("query") query : String?
    ) : Response<List<User>>

}