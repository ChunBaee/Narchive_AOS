package com.chunb.narchive.data.repository

import android.util.Log
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.source.BookSource
import com.chunb.narchive.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookRemoteSource: BookSource) :
    BookRepository {
    override suspend fun getBook(query : String?): Result<List<Book>> {
        return bookRemoteSource.getBooks(query)
    }
}