package com.chunb.narchive.data.repository

import android.util.Log
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.data.source.CommentSource
import com.chunb.narchive.domain.repository.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(private val commentRemoteSource: CommentSource) :
    CommentRepository {
    override suspend fun getLastComment(query : String?): Result<List<Comment>> {
        return commentRemoteSource.getLastComment(query)
    }
}