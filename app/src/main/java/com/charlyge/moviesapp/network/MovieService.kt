package com.charlyge.moviesapp.network

import com.charlyge.moviesapp.BuildConfig
import com.charlyge.moviesapp.models.MovieEntity
import com.charlyge.moviesapp.models.MovieResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieService @Inject internal constructor(private val movieServiceImpl: MovieServiceImpl) {

    suspend fun getPopularMovies():Flow<Response<MovieResults>> {
        return flow {
            emit(movieServiceImpl.popularMovies(BuildConfig.API_KEY,1))
        }
    }



}