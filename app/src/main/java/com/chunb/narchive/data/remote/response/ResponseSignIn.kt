package com.chunb.narchive.data.remote.response

data class ResponseSignIn(
    val userIdx : Int,
    val jwt : String,
    val isNew : String
)
