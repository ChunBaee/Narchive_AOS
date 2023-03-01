package com.chunb.narchive.presentation.ui.auth.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.data.remote.request.RequestPostSignIn
import com.chunb.narchive.data.remote.response.ResponseSignIn
import com.chunb.narchive.domain.repository.AuthRepository
import com.chunb.narchive.domain.repository.KakaoAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val kakaoAuthRepository: KakaoAuthRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isAuthSuccess = MutableLiveData<String>()
    val isAuthSuccess : LiveData<String> = _isAuthSuccess

    fun onKakaoAuthClick() {
        viewModelScope.launch {
            kakaoAuthRepository.initKakaoLogin()
                .onSuccess { authToRemote(it)}
                .onFailure { Log.d("----", "onKakaoAuthClick: $it") }
        }
    }

    private fun authToRemote(userId : Long) {
        viewModelScope.launch {
            authRepository.postSignIn(RequestPostSignIn(userId.toString()))
                .onSuccess { saveToLocal(it) }
        }
    }

    private fun saveToLocal(userInfo : ResponseSignIn) {
        viewModelScope.launch {
            authRepository.saveUserInfoInLocal(userInfo)
                .onSuccess { _isAuthSuccess.value = it }
        }
    }
}