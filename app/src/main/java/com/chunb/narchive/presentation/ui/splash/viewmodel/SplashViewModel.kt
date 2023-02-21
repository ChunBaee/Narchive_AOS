package com.chunb.narchive.presentation.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.data.remote.response.ResponseSignIn
import com.chunb.narchive.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    private val _isAuthSuccess = MutableLiveData<String>()
    val isAuthSuccess : LiveData<String> = _isAuthSuccess

    init {
        autoSignIn()
    }

    private fun autoSignIn() {
        viewModelScope.launch {
            authRepository.autoSignIn()
                .onSuccess { saveToLocal(it) }
                .onFailure { _isAuthSuccess.value = "FAIL" }
        }
    }

    private fun saveToLocal(userInfo : ResponseSignIn) {
        viewModelScope.launch {
            authRepository.saveUserInfoInLocal(userInfo)
                .onSuccess { _isAuthSuccess.value = it }
        }
    }

}