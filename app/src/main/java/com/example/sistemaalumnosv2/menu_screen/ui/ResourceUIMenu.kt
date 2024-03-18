package com.example.sistemaalumnosv2.menu_screen.ui

import com.example.sistemaalumnosv2.vo.Resource
import java.lang.Exception

sealed class ResourceUIMenu<out T> {
    class Loading<out T> :ResourceUIMenu<T>()
    data class Success<out T>(val data : T) : ResourceUIMenu<T>()
    data class Failure<out T>(val exception: Exception) : ResourceUIMenu<T>()
}
