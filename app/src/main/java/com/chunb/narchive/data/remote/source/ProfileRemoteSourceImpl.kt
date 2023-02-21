package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.remote.request.RequestPostProfile
import com.chunb.narchive.data.remote.service.ProfileService
import com.chunb.narchive.data.source.ProfileSource
import javax.inject.Inject

class ProfileRemoteSourceImpl @Inject constructor(private val profileService: ProfileService): ProfileSource {
    override suspend fun postUserProfile(userProfile: RequestPostProfile): Result<Int> {
        val profileRes = profileService.postUserProfile(userProfile)
        if(profileRes.isSuccessful) {
            return Result.success(profileRes.code())
        }
        return Result.failure(IllegalArgumentException())
    }
}