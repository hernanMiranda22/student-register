package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class GradeStudentRepoImpl:GradeStudentRepo {

    //Logica para ingresar los trimestres del alumno (GradeFragment)
    override suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int): Resource<Int> {

        val termMap = hashMapOf<String, Any>(
            "Trimestre 1" to firstTerm,
            "Trimestre 2" to secondTerm,
            "Trimestre 3" to thirdTerm
        )


        FirebaseFirestore.getInstance().collection("Student").document(dni.toString())
            .update(termMap).await()

        return Resource.Success(1)
    }
}