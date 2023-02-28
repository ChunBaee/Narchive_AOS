package com.chunb.narchive.data.repository

import android.util.Log
import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.data.source.ImageSource
import com.chunb.narchive.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val imageRemoteSource: ImageSource):
    ImageRepository {
    override suspend fun getImages(): Result<List<Image>> {
        return imageRemoteSource.getImages()
    }
}