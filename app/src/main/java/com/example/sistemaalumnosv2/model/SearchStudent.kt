package com.example.sistemaalumnosv2.model

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore

interface SearchStudent {

    suspend fun searchStudent(dni:Int):LiveData<MutableList<DataStudent>>
}