package com.chunb.narchive.data.remote.response

data class ResponseFeed (
    val user : User,
    val content : Content,
    var comments : List<Comment>?,
    val images : List<Image>?,
    val book : List<Book>?,
    val movie : List<Movie>?
)