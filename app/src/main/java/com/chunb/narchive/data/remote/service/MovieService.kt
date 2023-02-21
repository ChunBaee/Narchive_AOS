package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/archive/movie")
    suspend fun getMovie(
        @Query("query") query : String?
    ) : Response<List<Movie>>
}