package com.charlyge.moviesapp.network

import android.telecom.Call
import com.charlyge.moviesapp.models.MovieEntity
import com.charlyge.moviesapp.models.MovieResults
import dagger.Provides
import retrofit2.Response
import retrofit2.http.*

interface MovieServiceImpl {

    @GET("/3/movie/popular")
    suspend fun popularMovies(@Query("api_key") key: String): Response<MovieResults>



}