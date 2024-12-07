package com.example.sistemaalumnosv2.vo

import java.lang.Exception

sealed class Resource<out T> {
    data class Success<out T>(val data : T) : Resource<T>()
    data class Failure<out T>(val exception: Exception) : Resource<T>()
}

inline fun<T,R> Resource<T>.map(transform:(T) -> R): Resource<R>{
    return when(this){
        is Resource.Failure -> Resource.Failure(this.exception)
        is Resource.Success -> Resource.Success(transform(this.data))
    }
}
