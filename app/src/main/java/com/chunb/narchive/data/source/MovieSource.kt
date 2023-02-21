package com.chunb.narchive.data.source

import com.chunb.narchive.data.remote.response.Movie

interface MovieSource {

    suspend fun getMovies(query : String?) : Result<List<Movie>>
}