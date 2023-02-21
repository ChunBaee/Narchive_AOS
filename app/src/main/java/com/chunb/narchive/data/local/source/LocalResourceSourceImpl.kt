package com.chunb.narchive.data.local.source

import com.chunb.narchive.R
import com.chunb.narchive.data.local.data.OnBoardingData
import com.chunb.narchive.data.source.LocalResourceSource

class LocalResourceSourceImpl : LocalResourceSource{

    override suspend fun getOnBindingData(): List<OnBoardingData> =
        listOf(
            OnBoardingData(R.drawable.img_on_boarding_vp_1, "온보딩 첫번째"),
            OnBoardingData(R.drawable.img_on_boarding_vp_2, "온보딩 두번째"),
            OnBoardingData(R.drawable.img_on_boarding_vp_3, "온보딩 세번째")
        )

}