package com.example.sistemaalumnosv2.domain.signingooglecase

import com.example.sistemaalumnosv2.data.network.signingoogle.SignInUserGoogleRepo
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.AuthCredential

class SignInGoogleUseCaseImpl(private val signInUserGoogleRepo: SignInUserGoogleRepo):SignInGoogleUseCase {
    override suspend fun signInWithGoogle(credential: AuthCredential): Resource<Int> =
        signInUserGoogleRepo.signInWithGoogle(credential)
}