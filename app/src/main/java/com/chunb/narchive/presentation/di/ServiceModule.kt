package com.chunb.narchive.presentation.di

import com.chunb.narchive.data.remote.service.AuthService
import com.chunb.narchive.data.remote.service.BookService
import com.chunb.narchive.data.remote.service.CommentService
import com.chunb.narchive.data.remote.service.ContentService
import com.chunb.narchive.data.remote.service.ImageService
import com.chunb.narchive.data.remote.service.KakaoUserService
import com.chunb.narchive.data.remote.service.MovieService
import com.chunb.narchive.data.remote.service.ProfileService
import com.chunb.narchive.data.remote.service.SearchKakaoService
import com.chunb.narchive.data.remote.service.UserService
import com.chunb.narchive.presentation.di.annotation.KakaoAuthRetrofit
import com.chunb.narchive.presentation.di.annotation.KakaoRetrofit
import com.chunb.narchive.presentation.di.annotation.NarchiveRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideKakaoUserService(@KakaoAuthRetrofit retrofit : Retrofit) : KakaoUserService = retrofit.create(KakaoUserService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@NarchiveRetrofit retrofit: Retrofit) : AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideProfileService(@NarchiveRetrofit retrofit: Retrofit) : ProfileService = retrofit.create(ProfileService::class.java)

    @Provides
    @Singleton
    fun provideBookService(@NarchiveRetrofit retrofit: Retrofit) : BookService = retrofit.create(BookService::class.java)

    @Provides
    @Singleton
    fun provideCommentService(@NarchiveRetrofit retrofit: Retrofit) : CommentService = retrofit.create(CommentService::class.java)

    @Provides
    @Singleton
    fun provideContentService(@NarchiveRetrofit retrofit: Retrofit) : ContentService = retrofit.create(ContentService::class.java)

    @Provides
    @Singleton
    fun provideImageService(@NarchiveRetrofit retrofit: Retrofit) : ImageService = retrofit.create(ImageService::class.java)

    @Provides
    @Singleton
    fun provideMovieService(@NarchiveRetrofit retrofit: Retrofit) : MovieService = retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideUserService(@NarchiveRetrofit retrofit: Retrofit) : UserService = retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideSearchKakaoService(@KakaoRetrofit retrofit : Retrofit) : SearchKakaoService = retrofit.create(SearchKakaoService::class.java)
}