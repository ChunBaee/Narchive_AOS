package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.ResponseSignIn

interface SharedPrefSource {

    suspend fun saveUserInfo(userInfo : ResponseSignIn) : Result<String>

    suspend fun getUserId() : String

    suspend fun deleteUserJWT()
}