package com.example.sistemaalumnosv2.menu_screen.data.network.searchstudent

import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchStudentRepository @Inject constructor() {

    //Logica para obtener los datos especificados del alumno (OperationFragment)
    suspend fun searchStudent(uid: String): Resource<MutableList<DataStudent>> {

        try {
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
                        dni = dniStudent?.toInt() ?: 0,
                        name = nameStudent ?: "",
                        surname = surnameStudent ?: "",
                        year = yearStudent ?: "",
                        firstTerm = firstTermStudent?.toInt() ?: 0,
                        secondTerm = secondTermStudent?.toInt() ?: 0,
                        thirdTerm = thirdTermStudent?.toInt() ?: 0)
                )

            }
            return Resource.Success(listStudent)
        }catch (e : FirebaseFirestoreException){
            return Resource.Failure(e)
        }
    }

    suspend fun getAllStudents(uid: String) : Flow<MutableList<DataStudent>> = flow {
        val listStudent = mutableListOf<DataStudent>()


        withContext(Dispatchers.IO){
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
                        dni = dniStudent?.toInt() ?: 0,
                        name = nameStudent ?: "",
                        surname = surnameStudent ?: "",
                        year = yearStudent ?: "",
                        firstTerm = firstTermStudent?.toInt() ?: 0,
                        secondTerm = secondTermStudent?.toInt() ?: 0,
                        thirdTerm = thirdTermStudent?.toInt() ?: 0)
                )

            }
        }

        emit(listStudent)
    }
}