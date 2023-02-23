package com.chunb.narchive.data.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.chunb.narchive.data.source.FirebaseSource
import com.chunb.narchive.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(private val firebaseSource: FirebaseSource): FirebaseRepository {
    override suspend fun uploadProfileToFirebase(path: Uri, downloadURL: MutableLiveData<String>) {
        firebaseSource.uploadProfileToFirebase(path, downloadURL)
    }

    override suspend fun uploadWriteImageToFirebase(
        imageList: List<String>,
        downloadURL: MutableLiveData<MutableList<String>>
    ) {
        firebaseSource.uploadWriteImageToFirebase(imageList, downloadURL)
    }
}