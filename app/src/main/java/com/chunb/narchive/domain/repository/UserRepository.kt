package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.SettingUser
import com.chunb.narchive.data.remote.response.User


interface UserRepository {
    suspend fun getUserData() : Result<User>

    suspend fun getUserProfileData() : Result<SettingUser>
}