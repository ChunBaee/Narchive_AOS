package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.ArchiveBook
import com.chunb.narchive.data.remote.response.Book

interface BookSource {

    suspend fun getBooks() : Result<List<ArchiveBook>>
}