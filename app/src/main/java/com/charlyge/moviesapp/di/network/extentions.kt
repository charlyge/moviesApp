package com.charlyge.moviesapp.di.network

import com.charlyge.moviesapp.BuildConfig

fun debug(invoke: () -> Unit) {
    if (BuildConfig.DEBUG) {
        invoke()
    }
}
