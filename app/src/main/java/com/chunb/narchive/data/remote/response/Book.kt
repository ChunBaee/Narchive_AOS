package com.chunb.narchive.data.remote.response

import java.util.Date
import java.util.UUID

data class Book(
    val contentIdx : Int,
    val bookImageThumbnail: String,
    val bookTitle: String,
    var author: String,
    var publisher: String,
    var publishedDate: String
)


