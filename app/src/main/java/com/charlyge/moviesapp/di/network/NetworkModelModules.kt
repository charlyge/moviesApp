package com.charlyge.moviesapp.di.network

import android.app.Application
import com.charlyge.moviesapp.BuildConfig
import com.charlyge.moviesapp.network.MovieServiceImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModelModules {

    @Provides
    @Named("provideOkHttpClient")
    fun provideOkHttpClient(): OkHttpClient {
        val okhttp = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)

        debug {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            okhttp.addNetworkInterceptor(logging)
        }

        return okhttp.build()
    }

    @Provides
    @Named("provideRetrofitBuilder")
    fun provideRetrofitPreBuilder(@Named("provideOkHttpClient") provideOkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(provideOkHttpClient)
            .build()
    }


    @Provides
    fun provideMovieWebService(@Named("provideRetrofitBuilder") retrofit: Retrofit) : MovieServiceImpl{
        return retrofit.create(MovieServiceImpl::class.java)
    }


}