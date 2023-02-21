package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.data.remote.service.ImageService
import com.chunb.narchive.data.source.ImageSource
import javax.inject.Inject

class ImageRemoteSourceImpl @Inject constructor(private val imageService: ImageService): ImageSource {
    override suspend fun getImages(query : String?): Result<List<Image>> {
        val imageRes = imageService.getImages(query)
        if(imageRes.isSuccessful) {
            return Result.success((imageRes.body().orEmpty()))
        }
        return Result.failure(IllegalArgumentException())
    }
}