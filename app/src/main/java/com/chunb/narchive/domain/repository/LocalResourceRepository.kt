package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.data.ArchiveTabData
import com.chunb.narchive.data.data.SettingMenuData
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.data.local.data.OnBoardingData

interface LocalResourceRepository {

    suspend fun getOnBoardingData() : List<OnBoardingData>

    suspend fun getMoodsData() : List<MoodData>

    fun getTodayDate() : String

    fun getArchiveTabData() : List<ArchiveTabData>

    fun getSettingMenuData() : List<SettingMenuData>
}