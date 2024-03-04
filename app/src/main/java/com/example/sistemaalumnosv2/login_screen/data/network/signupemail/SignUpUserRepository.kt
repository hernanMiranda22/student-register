package com.example.sistemaalumnosv2.login_screen.data.network.signupemail

import com.example.sistemaalumnosv2.login_screen.data.model.User
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpUserRepository @Inject constructor(){
     suspend fun signUpEmailAndPassword(email: String, password: String): Resource<User> {

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email,password).await()
        auth.signOut()
        return Resource.Success(User(email, password))
    }
}