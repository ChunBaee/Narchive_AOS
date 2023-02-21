package com.chunb.narchive.data.remote.source.Firebase

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.chunb.narchive.data.source.FirebaseSource
import com.chunb.narchive.data.source.SharedPrefSource
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FirebaseRemoteSourceImpl @Inject constructor(private val sharedPrefSource: SharedPrefSource) : FirebaseSource {

    private val storage = FirebaseStorage.getInstance().reference

    override suspend fun uploadProfileToFirebase(path: Uri, downloadURL: MutableLiveData<String>) {
        val storagePath = "Image/${sharedPrefSource.getUserId()}/${path.lastPathSegment}"
        val ref = storage.child(storagePath)
        ref.putFile(path).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                downloadURL.value = it.toString()
            }
        }
    }
}