package com.chunb.narchive.data.remote.response

import java.io.Serializable

data class Image(
    val contentIdx : Int,
    val image : String
) : Serializable
