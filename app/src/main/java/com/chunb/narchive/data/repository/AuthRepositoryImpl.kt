package com.chunb.narchive.data.repository

import com.chunb.narchive.data.remote.request.RequestPostSignIn
import com.chunb.narchive.data.remote.response.ResponseSignIn
import com.chunb.narchive.data.source.AuthSource
import com.chunb.narchive.data.source.SharedPrefSource
import com.chunb.narchive.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteSource: AuthSource,
    private val sharedPrefSource: SharedPrefSource
) : AuthRepository {
    override suspend fun postSignIn(body: RequestPostSignIn): Result<ResponseSignIn> {
        return authRemoteSource.postSignIn(body)
    }

    override suspend fun saveUserInfoInLocal(userInfo: ResponseSignIn): Result<String> {
        return sharedPrefSource.saveUserInfo(userInfo)
    }

    override suspend fun autoSignIn(): Result<ResponseSignIn> {
        return authRemoteSource.autoSignIn()
    }

    override suspend fun deleteUserJWT() {
        sharedPrefSource.deleteUserJWT()
    }
}