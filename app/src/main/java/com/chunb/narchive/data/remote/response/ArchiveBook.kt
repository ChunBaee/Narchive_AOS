package com.chunb.narchive.data.remote.response


data class ArchiveBook(
    val contentIdx : Int,
    val bookImageThumbnail: String,
    val bookTitle: String,
    var author: String,
    var publisher: String,
    var publishedDate: String
)