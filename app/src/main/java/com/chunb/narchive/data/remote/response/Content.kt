package com.chunb.narchive.data.remote.response

data class Content (
    val user : User,
    val updatedAt : String,
    val content : String,
    var mood : String,
    val images : List<String>?,
    val book : List<Book>?,
    val movie : List<Movie>?
)