package com.example.sistemaalumnosv2.data.network.searchstudent

import com.example.sistemaalumnosv2.data.model.DataStudent
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SearchStudentRepoImpl @Inject constructor(): SearchStudentRepo {

    //Logica para obtener los datos especificados del alumno (OperationFragment)
    override suspend fun searchStudent(uid: String): Resource<MutableList<DataStudent>> {

        val listStudent = mutableListOf<DataStudent>()

        val dataStudent = FirebaseFirestore.getInstance()
            .collection("User").document(uid).collection("Student").get().await()

        for (student in dataStudent){
            val dniStudent = student.getLong("DNI")
            val nameStudent = student.getString("Nombre")
            val surnameStudent = student.getString("Apellido")
            val yearStudent = student.getString("Curso")
            val firstTermStudent = student.getLong("Trimestre 1")
            val secondTermStudent = student.getLong("Trimestre 2")
            val thirdTermStudent = student.getLong("Trimestre 3")

            listStudent.add(DataStudent(
                dniStudent?.toInt()!!,
                nameStudent!!,
                surnameStudent!!,
                yearStudent!!,
                firstTermStudent?.toInt()!!,
                secondTermStudent?.toInt()!!,
                thirdTermStudent?.toInt()!!))

        }

        return Resource.Success(listStudent)
    }
}