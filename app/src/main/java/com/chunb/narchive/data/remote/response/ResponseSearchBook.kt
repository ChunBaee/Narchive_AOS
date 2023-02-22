package com.chunb.narchive.data.remote.response

import java.io.Serializable

data class ResponseSearchBook(
    val meta : Meta,
    val documents : List<ResultSearchBook>
)
data class Meta (
    val is_end : Boolean,
    val pageable_count : Int,
    val total_count : Int
        )

data class ResultSearchBook(
    var title : String,
    var datetime : String,
    var authors : List<String>,
    var publisher : String,
    val thumbnail : String,
) : Serializable
