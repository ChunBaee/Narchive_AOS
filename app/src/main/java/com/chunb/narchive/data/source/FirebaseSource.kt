package com.chunb.narchive.data.source

import android.net.Uri
import androidx.lifecycle.MutableLiveData

interface FirebaseSource {

    suspend fun uploadProfileToFirebase(path : Uri, downloadURL : MutableLiveData<String>)

    suspend fun uploadWriteImageToFirebase(imageList : List<String>, downloadURL : MutableLiveData<MutableList<String>>)
}