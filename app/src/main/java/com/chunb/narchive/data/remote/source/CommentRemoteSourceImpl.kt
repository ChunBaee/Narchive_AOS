package com.chunb.narchive.data.remote.source

import android.util.Log
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.remote.service.CommentService
import com.chunb.narchive.data.source.CommentSource
import javax.inject.Inject

class CommentRemoteSourceImpl @Inject constructor(private val commentService: CommentService):
    CommentSource {
    override suspend fun getLastComment(query : String?): Result<List<Comment>> {
        val commentRes = commentService.getLastComments(query)
        if(commentRes.isSuccessful) {
            return Result.success((commentRes.body().orEmpty()))
        }
        return Result.failure(IllegalArgumentException())
    }
}