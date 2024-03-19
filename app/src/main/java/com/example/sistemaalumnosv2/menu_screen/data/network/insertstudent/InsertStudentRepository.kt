package com.example.sistemaalumnosv2.menu_screen.data.network.insertstudent

import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InsertStudentRepository @Inject constructor(){

    //Logica para ingresar los datos del alumno(StudentDataFragment)
   suspend fun insertStudent(dni:Int, name:String, surname:String, year:String, idUser: String): Resource<DataStudent> {

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

        return Resource.Success(DataStudent(dni = dni, name = name, surname = surname, year = year, firstTerm = 0, secondTerm = 0, thirdTerm = 0))

    }
}