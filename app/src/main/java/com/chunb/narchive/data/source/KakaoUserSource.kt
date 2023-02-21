package com.chunb.narchive.data.source

interface KakaoUserSource {

    suspend fun getUserIndex(token : String) : Result<Long>
}