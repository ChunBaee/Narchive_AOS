package com.chunb.narchive.data.remote.service

import com.chunb.narchive.data.remote.response.ArchiveBook
import com.chunb.narchive.data.remote.response.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/archive/book")
    suspend fun getBook() : Response<List<ArchiveBook>>
}