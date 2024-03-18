package com.example.sistemaalumnosv2.menu_screen.data.network.searchstudent

import com.example.sistemaalumnosv2.menu_screen.data.ResourceMenu
import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SearchStudentRepository @Inject constructor() {

    //Logica para obtener los datos especificados del alumno (OperationFragment)
    suspend fun searchStudent(uid: String): ResourceMenu<MutableList<DataStudent>> {

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

            listStudent.add(
                DataStudent(
                dniStudent?.toInt()!!,
                nameStudent!!,
                surnameStudent!!,
                yearStudent!!,
                firstTermStudent?.toInt()!!,
                secondTermStudent?.toInt()!!,
                thirdTermStudent?.toInt()!!)
            )

        }

        return ResourceMenu.Success(listStudent)
    }
}