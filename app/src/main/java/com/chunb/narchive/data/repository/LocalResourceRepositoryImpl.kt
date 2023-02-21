package com.chunb.narchive.data.repository

import com.chunb.narchive.data.local.data.OnBoardingData
import com.chunb.narchive.data.source.LocalResourceSource
import com.chunb.narchive.domain.repository.LocalResourceRepository
import javax.inject.Inject

class LocalResourceRepositoryImpl @Inject constructor(private val localResourceSource: LocalResourceSource): LocalResourceRepository {
    override suspend fun getOnBoardingData(): List<OnBoardingData> {
        return localResourceSource.getOnBindingData()
    }
}