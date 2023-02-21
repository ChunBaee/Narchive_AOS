package com.chunb.narchive.data.repository

import android.util.Log
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.data.source.MovieSource
import com.chunb.narchive.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieRemoteSource: MovieSource):
    MovieRepository {

    override suspend fun getMovies(query : String?): Result<List<Movie>> {
        return movieRemoteSource.getMovies(query)
    }

}