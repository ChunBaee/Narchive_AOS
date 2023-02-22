package com.chunb.narchive.data.remote.response

data class ResponseSearchMovie(
    val display: Int,
    val items: List<ResultSearchMovie>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
    )

data class ResultSearchMovie(
    var actor: String,
    var director: String,
    val image: String,
    var pubDate: String,
    var title: String,
)
