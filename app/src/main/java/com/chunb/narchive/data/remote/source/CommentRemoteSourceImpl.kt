package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.remote.request.RequestPostComment
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.remote.service.CommentService
import com.chunb.narchive.data.source.CommentSource
import com.chunb.narchive.presentation.util.BaseResponse
import javax.inject.Inject

class CommentRemoteSourceImpl @Inject constructor(private val commentService: CommentService):
    CommentSource {
    override suspend fun getComment(contentId : Int): Result<List<Comment>?> {
        val commentRes = commentService.getComments(contentId)
        if(commentRes.isSuccessful) {
            return Result.success((commentRes.body().orEmpty()))
        }
        return Result.failure(IllegalArgumentException())
    }

    override suspend fun postComment(contentId: Int, body: RequestPostComment): Result<String> {
        val commentRes = commentService.postComments(contentId, body)
        if(commentRes.isSuccessful) {
            return Result.success(commentRes.body()?.message.orEmpty())
        }
        return Result.failure(IllegalArgumentException())
    }
}