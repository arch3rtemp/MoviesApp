package com.arch3rtemp.android.moviesapp.util

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    object Empty : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: Exception) : Resource<Nothing>()
}
