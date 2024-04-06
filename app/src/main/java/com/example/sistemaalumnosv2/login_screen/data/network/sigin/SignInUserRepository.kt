package com.example.sistemaalumnosv2.login_screen.data.network.sigin

import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInUserRepository @Inject constructor() {
     suspend fun signInWithEmail(email: String, password: String): Resource<User> {
        val signInUser = FirebaseAuth.getInstance()

         signInUser.signInWithEmailAndPassword(email, password).await()

        return Resource.Success(User(email, password))
    }
}