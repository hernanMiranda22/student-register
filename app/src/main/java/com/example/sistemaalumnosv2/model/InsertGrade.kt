package com.example.sistemaalumnosv2.model

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore

interface InsertGrade {
    fun insertGradeStudent(grade : Int, dni : Int):LiveData<FirebaseFirestore>
}