package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SignUpUserRepoImpl:SignUpUserRepo {
    override suspend fun signUpEmailAndPassword(email: String, password: String): Resource<Int> {

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email,password).await()

        FirebaseFirestore.getInstance().collection("Student").document(auth.uid.toString())

        return Resource.Success(1)
    }
}