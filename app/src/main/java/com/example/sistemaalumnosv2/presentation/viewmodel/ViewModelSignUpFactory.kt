package com.example.sistemaalumnosv2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sistemaalumnosv2.domain.SignUpUserUseCase

class ViewModelSignUpFactory(private val signUpUserUseCase: SignUpUserUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SignUpUserUseCase::class.java).newInstance(signUpUserUseCase)
    }
}