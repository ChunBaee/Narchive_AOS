package com.chunb.narchive.data.repository

import com.chunb.narchive.data.source.KakaoAuthSource
import com.chunb.narchive.data.source.KakaoUserSource
import com.chunb.narchive.domain.repository.KakaoAuthRepository
import javax.inject.Inject

class KakaoAuthRepositoryImpl @Inject constructor(
    private val kakaoAuthRemoteSource: KakaoAuthSource,
    private val kakaoUserRemoteSource: KakaoUserSource
) : KakaoAuthRepository {

    override suspend fun initKakaoLogin(): Result<Long> {
        return kakaoUserRemoteSource.getUserIndex(kakaoAuthRemoteSource.initKakaoSignIn().setKakaoToken())
    }

    private fun String.setKakaoToken() : String = "Bearer $this"

}