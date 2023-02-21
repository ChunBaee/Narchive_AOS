package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.request.RequestPostProfile

interface ProfileSource {

    suspend fun postUserProfile (userProfile : RequestPostProfile) : Result<Int>
}