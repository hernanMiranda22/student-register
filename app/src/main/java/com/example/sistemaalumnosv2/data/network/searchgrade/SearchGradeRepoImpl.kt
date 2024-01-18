package com.example.sistemaalumnosv2.data.network.searchgrade

import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SearchGradeRepoImpl: SearchGradeRepo {

    //Logica para obtener los datos e ingresar los trimestres (GradeStudent)
    override suspend fun getGradeStudent(dni: Int,uid: String): Resource<MutableList<GradeStudent>> {
        val gradeList = mutableListOf<GradeStudent>()

        val studentData = FirebaseFirestore.getInstance()
            .collection("User").document(uid).collection("Student").document(dni.toString()).get().await()

        val name = studentData.getString("Nombre")
        val surname = studentData.getString("Apellido")
        val year = studentData.getString("Curso")
        val firstTerm = studentData.getLong("Trimestre 1")
        val secondTerm = studentData.getLong("Trimestre 2")
        val thirdTerm = studentData.getLong("Trimestre 3")

        gradeList.add(
            GradeStudent(name = name!!,
                surname = surname!!,
                year = year!!,
                firstTerm = firstTerm?.toInt()!!,
                secondTerm = secondTerm?.toInt()!!,
                thirdTerm = thirdTerm?.toInt()!!))

        return Resource.Success(gradeList)
    }


}