package com.devjcastro.androidtestmovies.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.devjcastro.androidtestmovies.MovieApplication
import android.support.v4.content.ContextCompat.getSystemService



class NetworkUtils {
    companion object {
        fun isOnline(): Boolean {
            val cm = MovieApplication.getAppContext()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }
    }
}