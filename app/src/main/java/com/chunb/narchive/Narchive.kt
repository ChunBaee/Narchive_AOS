package com.chunb.narchive

import android.app.Application
import android.app.Dialog
import android.content.SharedPreferences
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Narchive : Application() {

    companion object {
        lateinit var mSharedPreferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()

        mSharedPreferences = applicationContext.getSharedPreferences("Narchive", MODE_PRIVATE)

        initKakaoSDK()
    }

    private fun initKakaoSDK() {
        KakaoSdk.init(this, getString(R.string.KAKAO_NATIVE_KEY))
    }

}