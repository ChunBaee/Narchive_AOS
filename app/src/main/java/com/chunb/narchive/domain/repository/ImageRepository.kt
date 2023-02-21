package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.Image


interface ImageRepository {

    suspend fun getImages(query : String?) : Result<List<Image>>
}