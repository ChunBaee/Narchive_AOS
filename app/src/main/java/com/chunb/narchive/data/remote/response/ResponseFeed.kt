package com.chunb.narchive.data.remote.response

data class ResponseFeed (
    val userNickName : String,
    val userProfile : String,
    val content : String,
    val mood : String,
    val updatedAt : String,
    val commentNickName : String?,
    val commentContent : String?,
    val commentCount : Int,
    val bookTitle : String?,
    val bookAuthor : String?,
    val bookPublisher : String?,
    val bookPublishedDate : String?,
    val bookThumbnail : String?,
    val movieTitle : String?,
    val movieDirector : String?,
    val movieActor : String?,
    val movieReleaseYear : String?,
    val movieThumbnail : String?,
    val image : List<Image>,
    val contentIdx : Int

)