package com.example.sistemaalumnosv2.data.network.signingoogle

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.AuthCredential

interface SignInUserGoogleRepo {

    suspend fun signInWithGoogle(credential: AuthCredential):Resource<Int>
}