package com.chunb.narchive.data.remote.source.kakao

import android.content.Context
import com.chunb.narchive.data.source.KakaoAuthSource
import com.kakao.sdk.auth.TokenManagerProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class KakaoAuthRemoteSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : KakaoAuthSource {

    private val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { _, _ ->
    }

    override suspend fun initKakaoSignIn(): String {
        if (TokenManagerProvider.instance.manager.getToken()?.accessToken == null) {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                tryWithKakaoTalk()
            } else {
                checkWithKakaoAccount()
            }
        }
        return TokenManagerProvider.instance.manager.getToken()?.accessToken.toString()
    }

    override suspend fun tryWithKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                whenKakaoTalkHasError(error)
            }
            token?.accessToken
        }
    }

    override fun whenKakaoTalkHasError(error: Throwable) {
        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
            return
        }
        checkWithKakaoAccount()
    }

    override fun checkWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = kakaoCallback)
    }
}