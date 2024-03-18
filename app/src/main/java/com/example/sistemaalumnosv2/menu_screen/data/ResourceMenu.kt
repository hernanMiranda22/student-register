package com.example.sistemaalumnosv2.menu_screen.data

import com.example.sistemaalumnosv2.vo.Resource
import java.lang.Exception

sealed class ResourceMenu<out T>{
    data class Success<out T>(val data : T) : ResourceMenu<T>()
    data class Failure<out T>(val exception: Exception) : ResourceMenu<T>()
}
