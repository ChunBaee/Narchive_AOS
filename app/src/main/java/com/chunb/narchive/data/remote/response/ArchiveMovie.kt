package com.chunb.narchive.data.remote.response


data class ArchiveMovie(
    val contentIdx : Int,
    val movieImageThumbnail: String,
    val movieTitle: String,
    var director: String,
    var actor: String,
    var releaseYear: String
)