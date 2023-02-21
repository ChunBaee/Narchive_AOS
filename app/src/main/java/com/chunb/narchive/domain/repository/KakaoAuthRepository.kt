package com.chunb.narchive.domain.repository

interface KakaoAuthRepository {
    suspend fun initKakaoLogin() : Result<Long>
}