package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.User

interface UserSource {

    suspend fun getUserWithContentIdx(query : String?) : Result<List<User>>
}