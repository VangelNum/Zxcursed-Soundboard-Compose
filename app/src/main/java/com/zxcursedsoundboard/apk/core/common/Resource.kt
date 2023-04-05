package com.zxcursedsoundboard.apk.core.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}


sealed class ResourceFirebase<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : ResourceFirebase<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceFirebase<T>(data, message)
    class Loading<T>(data: T? = null) : ResourceFirebase<T>(data)
    class Empty<T> : ResourceFirebase<T>()
}