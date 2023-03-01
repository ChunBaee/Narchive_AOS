package com.chunb.narchive.data.local.source

import com.chunb.narchive.R
import com.chunb.narchive.data.data.ArchiveTabData
import com.chunb.narchive.data.data.SettingMenuData
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.data.local.data.OnBoardingData
import com.chunb.narchive.data.source.LocalResourceSource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocalResourceSourceImpl : LocalResourceSource {

    override suspend fun getOnBindingData(): List<OnBoardingData> =
        listOf(
            OnBoardingData(R.drawable.img_on_boarding_vp_1, "하고싶은 말은 많지만 할 수 없는 상황이 가끔 있죠?"),
            OnBoardingData(R.drawable.img_on_boarding_vp_2, "나만의 소중한 일상을 기록하고 싶지 않나요?"),
            OnBoardingData(R.drawable.img_on_boarding_vp_3, "나의 지난 시간들을 한눈에 모아보고 싶지는 않은가요?")
        )

    override suspend fun getMoodsData(): List<MoodData> =
        listOf(
            MoodData(Pair("normal", R.drawable.ic_face_default)),
            MoodData(Pair("angry", R.drawable.ic_angry_face)),
            MoodData(Pair("crying", R.drawable.ic_crying_face)),
            MoodData(Pair("heart", R.drawable.ic_heart_face)),
            MoodData(Pair("hush", R.drawable.ic_hushed_face)),
            MoodData(Pair("notGood", R.drawable.ic_not_good_face)),
            MoodData(Pair("sleepy", R.drawable.ic_sleepy_face)),
            MoodData(Pair("smile", R.drawable.ic_smile_face)),
            MoodData(Pair("sunglass", R.drawable.ic_sunglass_face))
        )

    override fun getTodayDate(): String {
        return SimpleDateFormat("yyyy.MM.dd", Locale.KOREA).format(Date(System.currentTimeMillis())).toString()
    }

    override fun getArchiveTabData(): List<ArchiveTabData> =
        listOf(
            ArchiveTabData(0, R.drawable.ic_write_open_book),
            ArchiveTabData(1, R.drawable.ic_write_open_movie),
            ArchiveTabData(2, R.drawable.ic_write_open_gallery)
        )

    override fun getSettingMenuData(): List<SettingMenuData> =
        listOf(
            SettingMenuData(R.drawable.ic_sign_out, "로그아웃"),
            SettingMenuData(R.drawable.ic_edit_profile, "프로필 수정"),
            SettingMenuData(R.drawable.ic_delete_user, "회원 탈퇴"),
        )


}