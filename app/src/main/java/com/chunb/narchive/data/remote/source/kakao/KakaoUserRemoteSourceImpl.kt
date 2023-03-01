package com.chunb.narchive.data.remote.source.kakao

import android.util.Log
import com.chunb.narchive.data.remote.service.KakaoUserService
import com.chunb.narchive.data.source.KakaoUserSource
import javax.inject.Inject

class KakaoUserRemoteSourceImpl @Inject constructor(private val kakaoUserService: KakaoUserService):
    KakaoUserSource {
    override suspend fun getUserIndex(token: String): Result<Long> {
        val kakaoUserRes = kakaoUserService.getUserId(token)
        Log.d("----", "getUserIndex: $token")
        if(kakaoUserRes.isSuccessful) {
            return Result.success(kakaoUserRes.body()?.id ?: throw IllegalArgumentException())
        }
        Log.d("----", "getUserIndex: ${kakaoUserRes.message()}")
        return Result.failure(IllegalArgumentException())
    }
}