package com.example.jumpingminds

sealed class Resource<T>(val data: T? = null, val message: String? = null, val isLoading: Boolean = false) {
    class Success<T>(data: T?): Resource<T>(data, isLoading = false)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message, isLoading = false)
    class Loading<T>(isLoading: Boolean = true): Resource<T>(null, isLoading = isLoading)
}




