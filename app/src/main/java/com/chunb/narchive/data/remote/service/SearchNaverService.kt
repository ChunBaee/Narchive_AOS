package com.chunb.nachive.data.remote.api.search

import com.chunb.narchive.data.remote.response.ResponseSearchMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchNaverService {
    @GET("/v1/search/movie.json")
    suspend fun getMovieInfoData(
        @Header("X-Naver-Client-Id") ClientId : String,
        @Header("X-Naver-Client-Secret") Secret : String,
        @Query("query", encoded = true) query : String,
        @Query("page") page : Int
    ) : Response<ResponseSearchMovie>
}