package com.chunb.narchive.data.repository

import android.util.Log
import com.chunb.narchive.data.remote.response.User
import com.chunb.narchive.data.source.UserSource
import com.chunb.narchive.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRemoteSource: UserSource):
    UserRepository {
    override suspend fun getUserWithContentId(query : String?): Result<List<User>> {
        return userRemoteSource.getUserWithContentIdx(query)
    }
}