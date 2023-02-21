package com.chunb.narchive.presentation.ui.onboarding.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunb.narchive.data.local.data.OnBoardingData
import com.chunb.narchive.domain.repository.LocalResourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val localResourceRepository: LocalResourceRepository): ViewModel() {
    private val _onBoardingData = MutableLiveData<List<OnBoardingData>>()
    val onBoardingData : LiveData<List<OnBoardingData>> = _onBoardingData

    init {
        getOnBoardingDatas()
    }

    private fun getOnBoardingDatas() {
        viewModelScope.launch {
            _onBoardingData.value = localResourceRepository.getOnBoardingData()
        }
    }
}