package com.example.sistemaalumnosv2.data.network.sigin

import com.example.sistemaalumnosv2.data.model.User
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInUserRepoImpl @Inject constructor():SignInUserRepo {
    override suspend fun signInWithEmail(email: String, password: String): Resource<User> {
        val signInUser = FirebaseAuth.getInstance()

        val signIn = signInUser.signInWithEmailAndPassword(email, password).await()
        val userEmail = signIn.user?.email

        return Resource.Success(User(userEmail!!))
    }
}