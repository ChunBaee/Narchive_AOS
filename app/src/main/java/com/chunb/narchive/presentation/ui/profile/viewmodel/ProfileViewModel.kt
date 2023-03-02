package com.chunb.narchive.presentation.ui.profile.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.R
import com.chunb.narchive.data.remote.request.RequestPostProfile
import com.chunb.narchive.domain.repository.FirebaseRepository
import com.chunb.narchive.domain.repository.ProfileRepository
import com.chunb.narchive.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val profileRepository: ProfileRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    init {
        getUserProfile()
    }

    val userNickName = MutableLiveData<String>().apply { }

    private val _userProfileImage = MutableLiveData<Any>(R.drawable.img_default_user)
    val userProfileImage: LiveData<Any> = _userProfileImage
    val userProfileImageDownloadURL = MutableLiveData<String>()

    private val _isProfileFinished = MutableLiveData<Boolean>()
    val isProfileFinished: LiveData<Boolean> = _isProfileFinished

    private val _remoteCode = MutableLiveData<Int>()
    val remoteCode : LiveData<Int> = _remoteCode

    val isNew = MutableLiveData<Boolean>()

    private fun getUserProfile() {
        viewModelScope.launch {
            userRepository.getUserData()
                .onSuccess {
                    setProfileImage(it.userProfileImage)
                    userNickName.value = it.userNickName
                    isNew.value = false
                }
                .onFailure { isNew.value = true }
        }
    }

    fun setProfileImage(path: Any) {
        _userProfileImage.value = path
    }

    private fun isImageSelected(): Boolean = userProfileImage.value != R.drawable.img_default_user

    private fun isNickNameInserted(): Boolean = userNickName.value?.length in 1..13

    fun setProfileFinished() {
        _isProfileFinished.value = isImageSelected() && isNickNameInserted()
    }

    fun uploadProfileToFirebase() {
        viewModelScope.launch {
            try {
                firebaseRepository.uploadProfileToFirebase(
                    userProfileImage.value as Uri,
                    userProfileImageDownloadURL
                )
            } catch(exception : ClassCastException) {
                postUserProfileToRemote(userProfileImage.value as String)
            }
        }
    }

    fun postUserProfileToRemote(downloadURL : String) {
        viewModelScope.launch {
            profileRepository.postUserProfile(RequestPostProfile(downloadURL, userNickName.value!!))
                .onSuccess { _remoteCode.value = it }
        }
    }
}