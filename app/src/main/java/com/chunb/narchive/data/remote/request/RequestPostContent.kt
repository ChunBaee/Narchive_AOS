package com.chunb.narchive.data.remote.request

import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Movie

data class RequestPostContent(
    val mood : String,
    val content : String,
    val images : List<String>?,
    val book : Book?,
    val movie : Movie?
    )