package com.example.sistemaalumnosv2.data.network.signingoogle

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInUserGoogleRepoImpl @Inject constructor():SignInUserGoogleRepo {
    override suspend fun signInWithGoogle(credential: AuthCredential): Resource<Int> {

        FirebaseAuth.getInstance().signInWithCredential(credential).await()

        return Resource.Success(1)
    }
}