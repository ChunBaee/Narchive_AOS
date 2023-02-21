package com.chunb.narchive.data.source

import android.net.Uri
import androidx.lifecycle.MutableLiveData

interface FirebaseSource {

    suspend fun uploadProfileToFirebase(path : Uri, downloadURL : MutableLiveData<String>)
}