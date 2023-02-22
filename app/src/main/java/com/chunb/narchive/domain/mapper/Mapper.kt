package com.chunb.narchive.domain.mapper

import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.ResultSearchBook

fun ResultSearchBook.mapToBook() : Book {
    return Book(this.thumbnail, this.title, "저자  " + this.authors.joinToString(","), "출판  " + this.publisher, "발매  " + this.datetime.substring(0, 10))
}