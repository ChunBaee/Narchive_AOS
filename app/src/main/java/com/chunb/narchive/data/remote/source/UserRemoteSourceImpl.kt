package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.remote.response.SettingUser
import com.chunb.narchive.data.remote.response.User
import com.chunb.narchive.data.remote.service.UserService
import com.chunb.narchive.data.source.UserSource
import javax.inject.Inject

class UserRemoteSourceImpl @Inject constructor(private val userService: UserService): UserSource {

    override suspend fun getUserData(): Result<User> {
        val userRes = userService.getUserData()
        if(userRes.isSuccessful) {
            return Result.success(userRes.body()!!)
        }
        return Result.failure(IllegalArgumentException())
    }

    override suspend fun getUserSettingProfileData(): Result<SettingUser> {
        val userProfileRes = userService.getUserSettingProfileData()
        if(userProfileRes.isSuccessful) {
            return Result.success(userProfileRes.body()!!)
        }
        return Result.failure(IllegalArgumentException())
    }
}