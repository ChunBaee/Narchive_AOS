package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.Image


interface ImageRepository {

    suspend fun getImages() : Result<List<Image>>
}