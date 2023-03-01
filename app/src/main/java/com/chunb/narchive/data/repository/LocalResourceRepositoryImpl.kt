package com.chunb.narchive.data.repository

import com.chunb.narchive.data.data.ArchiveTabData
import com.chunb.narchive.data.data.SettingMenuData
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.data.local.data.OnBoardingData
import com.chunb.narchive.data.source.LocalResourceSource
import com.chunb.narchive.domain.repository.LocalResourceRepository
import javax.inject.Inject

class LocalResourceRepositoryImpl @Inject constructor(private val localResourceSource: LocalResourceSource): LocalResourceRepository {
    override suspend fun getOnBoardingData(): List<OnBoardingData> {
        return localResourceSource.getOnBindingData()
    }

    override suspend fun getMoodsData(): List<MoodData> {
        return localResourceSource.getMoodsData()
    }

    override fun getTodayDate(): String {
        return localResourceSource.getTodayDate()
    }

    override fun getArchiveTabData(): List<ArchiveTabData> {
        return localResourceSource.getArchiveTabData()
    }

    override fun getSettingMenuData(): List<SettingMenuData> {
        return localResourceSource.getSettingMenuData()
    }
}