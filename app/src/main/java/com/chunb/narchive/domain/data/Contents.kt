package com.chunb.narchive.domain.data

import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.remote.response.Content
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.data.remote.response.User

data class Contents(
    val contentIdx : Int,
    var user : User,
    var content : Content,
    var book : Book?,
    var movie : Movie?,
    val images : List<String>?,
    var comments : Comment?
)
