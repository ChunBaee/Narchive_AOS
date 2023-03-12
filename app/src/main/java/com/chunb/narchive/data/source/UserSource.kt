package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.SettingUser
import com.chunb.narchive.data.remote.response.User

interface UserSource {
    suspend fun getUserData() : Result<User>

    suspend fun getUserSettingProfileData() : Result<SettingUser>
}