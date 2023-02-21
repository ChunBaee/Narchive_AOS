package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.request.RequestPostSignIn
import com.chunb.narchive.data.remote.response.ResponseSignIn

interface AuthSource {

    suspend fun postSignIn(body : RequestPostSignIn) : Result<ResponseSignIn>

    suspend fun autoSignIn() : Result<ResponseSignIn>
}