package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.Comment

interface CommentRepository {

    suspend fun getLastComment(query : String?) : Result<List<Comment>>
}