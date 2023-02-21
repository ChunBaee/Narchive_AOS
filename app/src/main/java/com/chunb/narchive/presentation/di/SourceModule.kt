package com.chunb.narchive.presentation.di

import com.chunb.narchive.data.remote.source.CommentRemoteSourceImpl
import com.chunb.narchive.data.remote.source.ContentRemoteSourceImpl
import com.chunb.narchive.data.remote.source.ImageRemoteSourceImpl
import com.chunb.narchive.data.remote.source.MovieRemoteSourceImpl
import com.chunb.narchive.data.remote.source.UserRemoteSourceImpl
import com.chunb.narchive.data.remote.source.AuthRemoteSourceImpl
import com.chunb.narchive.data.remote.source.BookRemoteSourceImpl
import com.chunb.narchive.data.remote.source.Firebase.FirebaseRemoteSourceImpl
import com.chunb.narchive.data.remote.source.ProfileRemoteSourceImpl
import com.chunb.narchive.data.remote.source.kakao.KakaoUserRemoteSourceImpl
import com.chunb.narchive.data.source.AuthSource
import com.chunb.narchive.data.source.BookSource
import com.chunb.narchive.data.source.CommentSource
import com.chunb.narchive.data.source.ContentSource
import com.chunb.narchive.data.source.FirebaseSource
import com.chunb.narchive.data.source.ImageSource
import com.chunb.narchive.data.source.KakaoUserSource
import com.chunb.narchive.data.source.MovieSource
import com.chunb.narchive.data.source.ProfileSource
import com.chunb.narchive.data.source.UserSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    abstract fun bindKakaoUserRemoteSource(kakaoUserRemoteSourceImpl: KakaoUserRemoteSourceImpl) : KakaoUserSource

    @Binds
    abstract fun bindAuthRemoteSource(authRemoteSourceImpl: AuthRemoteSourceImpl) : AuthSource

    @Binds
    abstract fun bindFirebaseRemoteSource(firebaseRemoteSourceImpl: FirebaseRemoteSourceImpl) : FirebaseSource

    @Binds
    abstract fun bindProfileRemoteSource(profileRemoteSourceImpl: ProfileRemoteSourceImpl) : ProfileSource

    @Binds
    abstract fun bindBookRemoteSource(bookRemoteSourceImpl: BookRemoteSourceImpl) : BookSource

    @Binds
    abstract fun bindCommentRemoteSource(commentRemoteSourceImpl: CommentRemoteSourceImpl) : CommentSource

    @Binds
    abstract fun bindContentRemoteSource(contentRemoteSourceImpl: ContentRemoteSourceImpl) : ContentSource

    @Binds
    abstract fun bindImageRemoteSource(imageRemoteSourceImpl: ImageRemoteSourceImpl) : ImageSource

    @Binds
    abstract fun bindMovieRemoteSource(movieRemoteSourceImpl: MovieRemoteSourceImpl) : MovieSource

    @Binds
    abstract fun bindUserRemoteSource(userRemoteSourceImpl: UserRemoteSourceImpl) : UserSource
}