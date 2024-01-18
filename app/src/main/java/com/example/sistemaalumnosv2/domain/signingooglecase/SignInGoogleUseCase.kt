package com.example.sistemaalumnosv2.domain.signingooglecase

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.AuthCredential

interface SignInGoogleUseCase {

    suspend fun signInWithGoogle(credential: AuthCredential): Resource<Int>
}