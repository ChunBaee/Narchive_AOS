package com.chunb.narchive.data.remote.source

import com.chunb.narchive.data.remote.response.ArchiveMovie
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.data.remote.service.MovieService
import com.chunb.narchive.data.source.MovieSource
import javax.inject.Inject

class MovieRemoteSourceImpl @Inject constructor(private val movieService: MovieService): MovieSource {
    override suspend fun getMovies(): Result<List<ArchiveMovie>> {
        val movieRes = movieService.getMovie()
        if(movieRes.isSuccessful) {
            return Result.success(movieRes.body()!!)
        }
        return Result.failure(IllegalArgumentException())
    }
}