package com.chunb.narchive.data.source

import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.data.local.data.OnBoardingData

interface LocalResourceSource {

    suspend fun getOnBindingData() : List<OnBoardingData>

    suspend fun getMoodsData() : List<MoodData>
}