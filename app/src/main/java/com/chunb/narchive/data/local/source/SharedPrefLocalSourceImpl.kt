package com.chunb.narchive.data.local.source

import com.chunb.narchive.Narchive.Companion.mSharedPreferences
import com.chunb.narchive.data.remote.response.ResponseSignIn
import com.chunb.narchive.data.source.SharedPrefSource

class SharedPrefLocalSourceImpl : SharedPrefSource {
    override suspend fun saveUserInfo(userInfo : ResponseSignIn): Result<String> {
        try {
            mSharedPreferences.edit().putString("userId", userInfo.userIdx.toString())
                .putString("jwt", userInfo.jwt).apply()
            return Result.success(userInfo.isNew)
        } catch (exception : Exception) {
            return Result.failure(exception)
        }
    }

    override suspend fun getUserId(): String {
        return mSharedPreferences.getString("userId", "NULL") ?: "NULL"
    }

    override suspend fun deleteUserJWT() {
        mSharedPreferences.edit().clear().apply()
    }
}