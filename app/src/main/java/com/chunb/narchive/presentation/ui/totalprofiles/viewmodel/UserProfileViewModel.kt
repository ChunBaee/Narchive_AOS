package com.chunb.narchive.presentation.ui.totalprofiles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userProfileImages = MutableLiveData<MutableList<String>>()
    val userProfileImages : LiveData<MutableList<String>> = _userProfileImages

    init {
        getUserProfileImages()
    }

    private fun getUserProfileImages() {
        viewModelScope.launch {
            userRepository.getUserAllProfileImages()
                .onSuccess { _userProfileImages.value = it as MutableList }
        }
    }
}