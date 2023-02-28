package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.ArchiveBook

interface BookRepository {

    suspend fun getBook() : Result<List<ArchiveBook>>
}