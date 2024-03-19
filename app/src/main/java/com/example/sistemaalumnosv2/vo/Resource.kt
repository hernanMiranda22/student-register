package com.example.sistemaalumnosv2.vo

import java.lang.Exception

sealed class Resource<out T> {
    data class Success<out T>(val data : T) : Resource<T>()
    data class Failure<out T>(val exception: Exception) : Resource<T>()
}
