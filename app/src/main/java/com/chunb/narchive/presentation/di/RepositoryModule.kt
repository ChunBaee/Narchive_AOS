package com.chunb.narchive.presentation.di

import com.chunb.narchive.data.repository.AuthRepositoryImpl
import com.chunb.narchive.data.repository.BookRepositoryImpl
import com.chunb.narchive.data.repository.CommentRepositoryImpl
import com.chunb.narchive.data.repository.ContentRepositoryImpl
import com.chunb.narchive.data.repository.FirebaseRepositoryImpl
import com.chunb.narchive.data.repository.ImageRepositoryImpl
import com.chunb.narchive.data.repository.KakaoAuthRepositoryImpl
import com.chunb.narchive.data.repository.LocalResourceRepositoryImpl
import com.chunb.narchive.data.repository.MovieRepositoryImpl
import com.chunb.narchive.data.repository.ProfileRepositoryImpl
import com.chunb.narchive.data.repository.SearchRepositoryImpl
import com.chunb.narchive.data.repository.UserRepositoryImpl
import com.chunb.narchive.domain.repository.AuthRepository
import com.chunb.narchive.domain.repository.BookRepository
import com.chunb.narchive.domain.repository.CommentRepository
import com.chunb.narchive.domain.repository.ContentRepository
import com.chunb.narchive.domain.repository.FirebaseRepository
import com.chunb.narchive.domain.repository.ImageRepository
import com.chunb.narchive.domain.repository.KakaoAuthRepository
import com.chunb.narchive.domain.repository.LocalResourceRepository
import com.chunb.narchive.domain.repository.MovieRepository
import com.chunb.narchive.domain.repository.ProfileRepository
import com.chunb.narchive.domain.repository.SearchRepository
import com.chunb.narchive.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindKakaoAuthRepository(kakaoAuthRepositroyImpl: KakaoAuthRepositoryImpl) : KakaoAuthRepository

    @Binds
    abstract fun bindAuthRepository (authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

    @Binds
    abstract fun bindFirebaseRepository (firebaseRepositoryImpl: FirebaseRepositoryImpl) : FirebaseRepository

    @Binds
    abstract fun bindProfileRepository (profileRepositoryImpl: ProfileRepositoryImpl) : ProfileRepository

    @Binds
    abstract fun bindLocalResourceRepository (localResourceRepositoryImpl: LocalResourceRepositoryImpl) : LocalResourceRepository

    @Binds
    abstract fun bindBookRepository (bookRepositoryImpl: BookRepositoryImpl) : BookRepository

    @Binds
    abstract fun bindCommentRepository (commentRepositoryImpl: CommentRepositoryImpl) : CommentRepository

    @Binds
    abstract fun bindContentRepository (contentRepositoryImpl: ContentRepositoryImpl) : ContentRepository

    @Binds
    abstract fun bindImageRepository (imageRepositoryImpl: ImageRepositoryImpl) : ImageRepository

    @Binds
    abstract fun bindMovieRepository (movieRepositoryImpl: MovieRepositoryImpl) : MovieRepository

    @Binds
    abstract fun bindUserRepository (userRepositoryImpl: UserRepositoryImpl) : UserRepository

    @Binds
    abstract fun bindSearchRepository (searchRepositoryImpl: SearchRepositoryImpl) : SearchRepository
}