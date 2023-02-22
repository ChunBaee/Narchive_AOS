package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.ResponseSearchBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchKakaoService {

    @GET("/v3/search/book")
    suspend fun getBookInfoData(
        @Header("Authorization") authorization : String,
        @Query("query", encoded = true) query : String,
        @Query("page") page : Int,
    ) : Response<ResponseSearchBook>
}