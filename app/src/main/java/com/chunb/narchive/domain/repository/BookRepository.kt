package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.Book

interface BookRepository {

    suspend fun getBook(query : String?) : Result<List<Book>>
}