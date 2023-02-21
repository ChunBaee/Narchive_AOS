package com.chunb.narchive.data.source

interface KakaoAuthSource {

    suspend fun initKakaoSignIn() : String

    suspend fun tryWithKakaoTalk()

    fun whenKakaoTalkHasError(error : Throwable)

    fun checkWithKakaoAccount()

}