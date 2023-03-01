package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.remote.request.RequestPostSignIn
import com.chunb.narchive.data.remote.response.ResponseSignIn
import com.chunb.narchive.data.remote.service.AuthService
import com.chunb.narchive.data.source.AuthSource
import javax.inject.Inject

class AuthRemoteSourceImpl @Inject constructor(private val authService: AuthService): AuthSource {
    override suspend fun postSignIn(body : RequestPostSignIn): Result<ResponseSignIn> {
        val authRes = authService.postSignIn(body)
        if(authRes.isSuccessful) {
            return Result.success(authRes.body() ?: throw IllegalArgumentException())
        }
        return Result.failure(IllegalArgumentException())
    }

    override suspend fun autoSignIn(): Result<ResponseSignIn> {
        val autoSignRes = authService.autoSignIn()
        if(autoSignRes.isSuccessful) {
            return Result.success(autoSignRes.body() ?: throw IllegalArgumentException())
        }
        return Result.failure(IllegalArgumentException())
    }

    override suspend fun deleteUser(): Result<String> {
        val authRes = authService.deleteUser()
        if(authRes.isSuccessful) {
            return Result.success(authRes.message())
        }
        return Result.failure(IllegalArgumentException())
    }


}