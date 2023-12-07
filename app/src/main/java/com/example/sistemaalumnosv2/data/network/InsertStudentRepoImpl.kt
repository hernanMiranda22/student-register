package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class InsertStudentRepoImpl:InsertStudentRepo {

    //Logica para ingresar los datos del alumno
    override suspend fun insertStudent(dni:Int, name:String, surname:String, year:String): Resource<Int> {

        val studentMap = hashMapOf(
            "DNI" to dni,
            "Nombre" to name,
            "Apellido" to surname,
            "Curso" to year,
            "Nota" to 0
        )

        //Logica de firebase
        FirebaseFirestore.getInstance().collection("Student")
            .document(dni.toString())
            .set(studentMap).await()

        return Resource.Success(1)

    }
}