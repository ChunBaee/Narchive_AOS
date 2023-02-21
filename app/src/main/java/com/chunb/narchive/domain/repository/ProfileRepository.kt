package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.request.RequestPostProfile

interface ProfileRepository {

    suspend fun postUserProfile(userProfile : RequestPostProfile) : Result<Int>
}