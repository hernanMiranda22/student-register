package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.data.DataStudent
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SearchStudentRepoImpl:SearchStudentRepo {

    //Logica para obtener los datos especificados del alumno
    override suspend fun searchStudent(dni:Int): Resource<MutableList<DataStudent>> {

        val listStudent = mutableListOf<DataStudent>()

        val dataStudent = FirebaseFirestore.getInstance()
            .collection("Student").document(dni.toString()).get().await()

        val surnameStudent = dataStudent.getString("Apellido")
        val yearStudent = dataStudent.getString("Curso")
        val gradeStudent = dataStudent.getLong("Nota")

         listStudent.add(DataStudent(surnameStudent!!, yearStudent!!, gradeStudent!!.toInt()))

        return Resource.Success(listStudent)
    }
}