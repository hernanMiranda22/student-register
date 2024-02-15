package com.example.sistemaalumnosv2.data.network.insertstudent

import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InsertStudentRepoImpl @Inject constructor(): InsertStudentRepo {

    //Logica para ingresar los datos del alumno(StudentDataFragment)
    override suspend fun insertStudent(dni:Int, name:String, surname:String, year:String, idUser: String): Resource<Int> {

        val studentMap = hashMapOf(
            "DNI" to dni,
            "Nombre" to name,
            "Apellido" to surname,
            "Curso" to year,
            "Trimestre 1" to 0,
            "Trimestre 2" to 0,
            "Trimestre 3" to 0

        )

        //Logica de firebase
        FirebaseFirestore.getInstance().collection("User")
            .document(idUser).collection("Student").document(dni.toString())
            .set(studentMap).await()

        return Resource.Success(1)

    }
}