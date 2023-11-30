package com.example.sistemaalumnosv2.model

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertGradeRepository:InsertGrade {
    override fun insertGradeStudent(grade: Int, dni : Int): LiveData<FirebaseFirestore> {
        val mutableGrade = MutableLiveData<FirebaseFirestore>()

        CoroutineScope(Dispatchers.IO).launch {
            val fireStore = FirebaseFirestore.getInstance()

            fireStore.collection("Student")
                .document(dni.toString()).update(
                    mapOf("Nota" to grade)
                ).addOnCompleteListener {
                    Log.e(TAG, "Nota ingresada: $it")
                    mutableGrade.postValue(fireStore)
                }.addOnFailureListener {
                    Log.e(TAG, "Error en la nota: $it")
                }
        }
        return mutableGrade
    }
}