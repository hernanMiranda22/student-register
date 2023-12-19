package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.data.model.DataStudent
import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SearchStudentRepoImpl:SearchStudentRepo {

    //Logica para obtener los datos especificados del alumno
    override suspend fun searchStudent(dni:Int): Resource<MutableList<GradeStudent>> {

        val listStudent = mutableListOf<GradeStudent>()

        val dataStudent = FirebaseFirestore.getInstance()
            .collection("Student").document(dni.toString()).get().await()

        val nameStudent = dataStudent.getString("Nombre")
        val surnameStudent = dataStudent.getString("Apellido")
        val yearStudent = dataStudent.getString("Curso")
        val firstTermStudent = dataStudent.getLong("Trimestre 1")
        val secondTermStudent = dataStudent.getLong("Trimestre 2")
        val thirdTermStudent = dataStudent.getLong("Trimestre 3")

         listStudent.add(GradeStudent(nameStudent!!,surnameStudent!!, yearStudent!!, firstTermStudent?.toInt()!!, secondTermStudent?.toInt()!!, thirdTermStudent?.toInt()!!))

        return Resource.Success(listStudent)
    }
}