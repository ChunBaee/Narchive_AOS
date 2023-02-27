package com.chunb.narchive.data.remote.response

data class Feed (
    val contentIdx : Int,
    val user : User,
    val updatedAt : String,
    val commentCount : Int,
    val bookExists : Boolean,
    val movieExists : Boolean,
    val imageCount : Int,
    val mood : String,
    val content : String
)