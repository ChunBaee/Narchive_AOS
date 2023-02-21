package com.chunb.narchive.data.repository

import com.chunb.narchive.data.remote.request.RequestPostProfile
import com.chunb.narchive.data.source.ProfileSource
import com.chunb.narchive.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val profileSource: ProfileSource): ProfileRepository {
    override suspend fun postUserProfile(userProfile: RequestPostProfile): Result<Int> {
        return profileSource.postUserProfile(userProfile)
    }
}