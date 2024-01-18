package com.example.sistemaalumnosv2.data.network.signupemail

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SignUpUserRepoImpl: SignUpUserRepo {
    override suspend fun signUpEmailAndPassword(email: String, password: String): Resource<Int> {

        val studentMap = hashMapOf(
            "DNI" to "",
            "Nombre" to "",
            "Apellido" to "",
            "Curso" to "",
            "Trimestre 1" to 0,
            "Trimestre 2" to 0,
            "Trimestre 3" to 0

        )

        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email,password).await()

        FirebaseFirestore.getInstance().collection("Student").document(auth.uid.toString()).set(studentMap)

        return Resource.Success(1)
    }
}