package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.request.RequestPostSignIn
import com.chunb.narchive.data.remote.response.ResponseSignIn

interface AuthRepository {

    suspend fun postSignIn(body : RequestPostSignIn) : Result<ResponseSignIn>

    suspend fun saveUserInfoInLocal (userInfo : ResponseSignIn) : Result<String>

    suspend fun autoSignIn() : Result<ResponseSignIn>

    suspend fun deleteUserJWT()
}