package com.example.sistemaalumnosv2.menu_screen.ui.model

import java.lang.Exception

sealed interface ResourceMenu {

    data object Loading:ResourceMenu
    data class Success(val data : Any) : ResourceMenu
    data class Failure(val exception: Throwable) : ResourceMenu
}