package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.source.BookSource
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.service.BookService
import javax.inject.Inject

class BookRemoteSourceImpl @Inject constructor(private val bookService: BookService): BookSource {

    override suspend fun getBooks(query : String?): Result<List<Book>> {
        val bookRes = bookService.getBook(query)
        if(bookRes.isSuccessful) {
            return Result.success((bookRes.body()).orEmpty())
        }
        return Result.failure(IllegalArgumentException())
    }
}