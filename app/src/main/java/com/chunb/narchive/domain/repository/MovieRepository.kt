package com.chunb.narchive.domain.repository

import com.chunb.narchive.data.remote.response.Movie


interface MovieRepository {

    suspend fun getMovies(query : String?) : Result<List<Movie>>
}