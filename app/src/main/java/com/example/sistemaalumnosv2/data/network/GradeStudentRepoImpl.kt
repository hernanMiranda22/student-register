package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class GradeStudentRepoImpl:GradeStudentRepo {
    override suspend fun insertGrade(dni:Int, grade:Int): Resource<Int> {

        val gradeStudent = FirebaseFirestore.getInstance().collection("Student").document(dni.toString())
            .update(mapOf("Nota" to grade)).await()

        return Resource.Success(grade)
    }
}