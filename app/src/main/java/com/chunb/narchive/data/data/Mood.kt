package com.chunb.narchive.data.data

import com.chunb.narchive.R

enum class Mood (val type : String, val res : Int) {
    normal("normal", R.drawable.ic_face_default),
    angry("angry", R.drawable.ic_angry_face),
    crying("crying", R.drawable.ic_crying_face),
    heart("heart", R.drawable.ic_heart_face),
    hush("hush", R.drawable.ic_hushed_face),
    notGood("notGood", R.drawable.ic_not_good_face),
    sleepy("sleepy", R.drawable.ic_sleepy_face),
    smile("smile", R.drawable.ic_smile_face),
    sunglass("sunglass", R.drawable.ic_sunglass_face)
}