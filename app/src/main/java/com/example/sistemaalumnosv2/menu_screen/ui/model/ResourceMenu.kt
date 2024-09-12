package com.example.sistemaalumnosv2.menu_screen.ui.model

sealed interface ResourceMenu {
    data object Loading:ResourceMenu
    data class Success(val data : List<DataStudentUI>) : ResourceMenu
    data class Failure(val exception: Throwable) : ResourceMenu
}