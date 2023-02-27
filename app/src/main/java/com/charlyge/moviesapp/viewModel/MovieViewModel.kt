package com.charlyge.moviesapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charlyge.moviesapp.models.MovieEntity
import com.charlyge.moviesapp.models.MovieResults
import com.charlyge.moviesapp.network.MovieService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val service: MovieService) :
    ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _movieResponse: MutableLiveData<MovieResults> =
        MutableLiveData()
    val movieResponse: MutableLiveData<MovieResults>
        get() {
            return _movieResponse
        }

    private val _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: MutableLiveData<String>
        get() {
            return _errorResponse
        }

    fun getPopularMovies() {
        coroutineScope.launch {
            try {
                val response = service.getPopularMovies()
                response.collect {
                    if (it.code() == 401 || it.code() == 400) {
                        Log.d("failed", it.code().toString())

                        _errorResponse.value = it.code().toString()
                    } else if (it.code() == 200) {
                        Log.d("@response.body()", it.body().toString())

                            _movieResponse.value = it.body()

                    }
                }

            } catch (e: Exception) {
                _errorResponse.value = e.localizedMessage
            }
        }
    }
}