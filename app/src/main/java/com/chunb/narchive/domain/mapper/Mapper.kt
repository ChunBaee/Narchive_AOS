package com.chunb.narchive.domain.mapper

import android.text.Html
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.data.remote.response.ResultSearchBook
import com.chunb.narchive.data.remote.response.ResultSearchMovie

fun ResultSearchBook.mapToBook(): Book {
    return Book(
        this.thumbnail,
        this.title,
        "저자  " + this.authors.joinToString(","),
        "출판  " + this.publisher,
        "발매  " + this.datetime.substring(0, 10)
    )
}

fun ResultSearchMovie.mapToMovie(): Movie {
    return Movie(
        this.image,
        Html.fromHtml(this.title, 0).toString(),
        "감독  " + this.director.replace("|", " "),
        "배우  " + this.actor.replace("|", " "),
        "개봉년도  " + this.pubDate
    )
}