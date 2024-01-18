package com.example.sistemaalumnosv2.presentation.viewmodel.signinviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sistemaalumnosv2.domain.signincase.SignInUseCase
import com.example.sistemaalumnosv2.domain.signingooglecase.SignInGoogleUseCase

class ViewModelSignInFactory(private val signInUseCase: SignInUseCase, private val signInGoogleUseCase: SignInGoogleUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SignInUseCase::class.java, SignInGoogleUseCase::class.java).newInstance(signInUseCase, signInGoogleUseCase)
    }
}