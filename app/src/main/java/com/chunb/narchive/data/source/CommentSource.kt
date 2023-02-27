package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.request.RequestPostComment
import com.chunb.narchive.data.remote.response.Comment
import com.chunb.narchive.presentation.util.BaseResponse

interface CommentSource {

    suspend fun getComment(contentId : Int) : Result<List<Comment>?>

    suspend fun postComment(contentId : Int, body : RequestPostComment) : Result<String>
}