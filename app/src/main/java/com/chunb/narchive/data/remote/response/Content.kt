package com.chunb.narchive.data.remote.response

data class Content (
    val updatedAt : String,
    val content : String,
    val mood : String,
    var commentCount : Int
)