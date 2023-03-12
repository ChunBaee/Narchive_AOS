package com.chunb.narchive.data.remote.response

data class SettingUser(
    val userProfileImage: String,
    var userNickName: String,
    val contentCount : Int,
    val bookCount : Int,
    val movieCount : Int
)
