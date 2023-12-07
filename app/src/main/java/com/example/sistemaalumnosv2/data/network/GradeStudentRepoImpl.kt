package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class GradeStudentRepoImpl:GradeStudentRepo {

    //Logica de firebase que actualiza el campo "Nota" para calificar al alumno
    override suspend fun insertGrade(dni:Int, grade:Int): Resource<Int> {

        FirebaseFirestore.getInstance().collection("Student").document(dni.toString())
            .update(mapOf("Nota" to grade)).await()

        return Resource.Success(grade)
    }
}