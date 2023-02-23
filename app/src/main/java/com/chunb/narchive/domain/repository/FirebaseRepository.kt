package com.chunb.narchive.domain.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData

interface FirebaseRepository {

    suspend fun uploadProfileToFirebase(path : Uri, downloadURL : MutableLiveData<String>)

    suspend fun uploadWriteImageToFirebase(imageList : List<String>, downloadURL: MutableLiveData<MutableList<String>>)
}