package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("/archive/images")
    suspend fun getImages() : Response<List<Image>>
}