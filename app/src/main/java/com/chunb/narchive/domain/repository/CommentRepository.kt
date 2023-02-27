package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.request.RequestPostComment
import com.chunb.narchive.data.remote.response.Comment

interface CommentRepository {

    suspend fun getComment(contentId : Int) : Result<List<Comment>?>

    suspend fun postComment(contentId : Int, body : RequestPostComment) : Result<String>
}