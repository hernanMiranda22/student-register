package com.example.sistemaalumnosv2.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateStudentRepository:CreateStudent {

    private val fireStore = Firebase.firestore

    override suspend fun createStudent(
        dni: Int, name: String, surname: String, year: String
    ): LiveData<FirebaseFirestore> {

        val mutableStudent = MutableLiveData<FirebaseFirestore>()

        CoroutineScope(Dispatchers.IO).launch {

            val studentMap = hashMapOf(
                "Nombre" to name,
                "Apellido" to surname,
                "Curso" to year
            )

            fireStore.collection("Student").document(dni.toString())
                .set(studentMap)
                .addOnCompleteListener {
                    mutableStudent.postValue(fireStore)
                    Log.e(TAG, "Estudiante ingresado")
                }
                .addOnFailureListener { task ->
                    Log.e(TAG, "Error al ingresar: $task")
                }
        }

        return mutableStudent
    }
}