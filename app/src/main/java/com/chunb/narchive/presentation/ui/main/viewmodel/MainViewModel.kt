package com.chunb.narchive.presentation.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chunb.narchive.data.data.ArchiveTabData
import com.chunb.narchive.data.data.SettingMenuData
import com.chunb.narchive.data.remote.response.ArchiveBook
import com.chunb.narchive.data.remote.response.ArchiveMovie
import com.chunb.narchive.data.remote.response.Feed
import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.data.remote.response.User
import com.chunb.narchive.domain.repository.AuthRepository
import com.chunb.narchive.domain.repository.BookRepository
import com.chunb.narchive.domain.repository.ContentRepository
import com.chunb.narchive.domain.repository.ImageRepository
import com.chunb.narchive.domain.repository.KakaoAuthRepository
import com.chunb.narchive.domain.repository.LocalResourceRepository
import com.chunb.narchive.domain.repository.MovieRepository
import com.chunb.narchive.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contentRepository: ContentRepository,
    private val localResourceRepository: LocalResourceRepository,
    private val bookRepository: BookRepository,
    private val movieRepository : MovieRepository,
    private val imageRepository: ImageRepository,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val kakaoAuthRepository: KakaoAuthRepository
) : ViewModel() {

    private val _archiveBookData = MutableLiveData<MutableList<ArchiveBook>>()
    val archiveBookData : LiveData<MutableList<ArchiveBook>> = _archiveBookData
    private val _archiveMovieData = MutableLiveData<MutableList<ArchiveMovie>>()
    val archiveMovieData : LiveData<MutableList<ArchiveMovie>> = _archiveMovieData
    private val _archiveImageData = MutableLiveData<MutableList<Image>>()
    val archiveImageData : LiveData<MutableList<Image>> = _archiveImageData

    private val _settingMenuData = MutableLiveData<List<SettingMenuData>>().apply { value = localResourceRepository.getSettingMenuData() }
    val settingMenuData : LiveData<List<SettingMenuData>> = _settingMenuData

    private val _userData = MutableLiveData<User>()
    val userData : LiveData<User> = _userData

    private val _dialogTitle = MutableLiveData<String>()
    val dialogTitle : LiveData<String> = _dialogTitle

    private val _dialogType = MutableLiveData<Int>()
    val dialogType : LiveData<Int> = _dialogType

    fun getFeedData() : Flow<PagingData<Feed>> {
        return contentRepository.getFeedPagingData()
    }

    fun getArchiveTabData() : List<ArchiveTabData> = localResourceRepository.getArchiveTabData()

    fun getArchiveData() {
        viewModelScope.launch {
            bookRepository.getBook()
                .onSuccess { _archiveBookData.value = it as MutableList}
            movieRepository.getMovies()
                .onSuccess { _archiveMovieData.value = it as MutableList }
            imageRepository.getImages()
                .onSuccess { _archiveImageData.value = it as MutableList }
        }
    }

    fun getUserRes() {
        viewModelScope.launch {
            userRepository.getUserData()
                .onSuccess { _userData.value = it }
        }
    }

    private fun initSignOut() {
        viewModelScope.launch {
            authRepository.deleteUserJWT()
            kakaoAuthRepository.initKakaoSignOut()
        }
    }

    private fun initDeleteUser() {
        viewModelScope.launch {
            authRepository.deleteUser()
            authRepository.deleteUserJWT()
            kakaoAuthRepository.initKakaoSignOut()
        }
    }

    fun setDialogType(type : Int) {
        _dialogType.value = type
        _dialogTitle.value = when(type) {
            0 ->  "정말 로그아웃 하시겠어요?"
            else -> "정말 탈퇴하시겠어요?"
        }
    }

    fun onDialogClickYes() {
        when(_dialogType.value) {
            0 -> initSignOut()
            else -> initDeleteUser()
        }
    }
}