package com.chunb.narchive.domain.mapper

import android.text.Html
import com.chunb.narchive.data.remote.request.RequestPostContent
import com.chunb.narchive.data.remote.response.ArchiveBook
import com.chunb.narchive.data.remote.response.ArchiveMovie
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

fun String.mapToFeedData(mood : String, imageList : MutableList<String>?, book : Book?, movie : Movie?) : RequestPostContent {
    return RequestPostContent(
        mood,
        this,
        imageList,
        book,
        movie
    )
}

fun ArchiveBook.mapToBook() : Book {
    return Book(
        this.bookImageThumbnail,
        this.bookTitle,
        this.author,
        this.publisher,
        this.publishedDate
    )
}

fun ArchiveMovie.mapToMovie() : Movie {
    return Movie(
        this.movieImageThumbnail,
        this.movieTitle,
        this.director,
        this.actor,
        this.releaseYear
    )
}