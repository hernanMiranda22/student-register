package com.example.sistemaalumnosv2.data.network.sigin

import android.util.Log
import com.example.sistemaalumnosv2.data.model.User
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class SignInUserRepoImpl:SignInUserRepo {
    override suspend fun signInWithEmail(email: String, password: String): Resource<User> {
        val signInUser = FirebaseAuth.getInstance()

        val signIn = signInUser.signInWithEmailAndPassword(email, password).await()
        val userEmail = signIn.user?.email

        return Resource.Success(User(userEmail!!))
    }
}