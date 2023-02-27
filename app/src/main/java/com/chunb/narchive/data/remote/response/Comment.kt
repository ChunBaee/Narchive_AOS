package com.chunb.narchive.data.remote.response

data class Comment(
    val commentId: Int,
    val content: String,
    val nickName: String,
    val userProfileImage: String?,
    val updatedAt: String?
)