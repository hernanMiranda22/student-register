package com.example.sistemaalumnosv2.menu_screen.data.network.insertgrade

import com.example.sistemaalumnosv2.menu_screen.data.model.TermData
import com.example.sistemaalumnosv2.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GradeStudentRepository @Inject constructor() {

    //Logica para ingresar los trimestres del alumno (GradeFragment)
     suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String): Resource<TermData> {

        val termMap = hashMapOf<String, Any>(
            "Trimestre 1" to firstTerm,
            "Trimestre 2" to secondTerm,
            "Trimestre 3" to thirdTerm
        )


        FirebaseFirestore.getInstance().collection("User").document(uid).collection("Student").document(dni.toString())
            .update(termMap).await()

        return Resource.Success(TermData(firstTerm =  firstTerm, secondTerm = secondTerm, thirdTerm = thirdTerm))
    }

}