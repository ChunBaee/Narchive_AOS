package com.chunb.narchive.data.remote.response

import java.io.Serializable

data class Movie(
    val movieImageThumbnail: String?,
    val movieTitle: String?,
    var director: String?,
    var actor: String?,
    var releaseYear: String?
) : Serializable