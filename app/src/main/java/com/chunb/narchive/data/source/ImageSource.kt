package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.Image


interface ImageSource {

    suspend fun getImages(query : String?) : Result<List<Image>>
}