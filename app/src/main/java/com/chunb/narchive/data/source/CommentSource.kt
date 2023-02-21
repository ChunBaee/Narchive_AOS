package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.Comment

interface CommentSource {

    suspend fun getLastComment(query : String?) : Result<List<Comment>>
}