package com.chunb.narchive.data.source

import com.chunb.narchive.data.data.ArchiveTabData
import com.chunb.narchive.data.data.SettingMenuData
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.data.local.data.OnBoardingData

interface LocalResourceSource {

    suspend fun getOnBindingData() : List<OnBoardingData>

    suspend fun getMoodsData() : List<MoodData>

    fun getTodayDate() : String

    fun getArchiveTabData() : List<ArchiveTabData>

    fun getSettingMenuData() : List<SettingMenuData>
}