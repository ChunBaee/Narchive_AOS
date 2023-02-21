package com.chunb.narchive.presentation.di

import android.content.Context
import com.chunb.narchive.data.local.source.LocalResourceSourceImpl
import com.chunb.narchive.data.local.source.SharedPrefLocalSourceImpl
import com.chunb.narchive.data.remote.source.kakao.KakaoAuthRemoteSourceImpl
import com.chunb.narchive.data.source.KakaoAuthSource
import com.chunb.narchive.data.source.LocalResourceSource
import com.chunb.narchive.data.source.SharedPrefSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context : Context) : Context = context

    @Provides
    @Singleton
    fun provideKakaoAuthRemoteSource(context : Context): KakaoAuthSource = KakaoAuthRemoteSourceImpl(context)

    @Provides
    @Singleton
    fun provideSharedPrefSource() : SharedPrefSource = SharedPrefLocalSourceImpl()

    @Provides
    @Singleton
    fun bindLocalResourceSource() : LocalResourceSource = LocalResourceSourceImpl()
}