package com.example.sistemaalumnosv2.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sistemaalumnosv2.data.DataStudent
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchStudentRepository:SearchStudent {

    private val fireStore =  Firebase.firestore
    override suspend fun searchStudent(dni: Int): LiveData<MutableList<DataStudent>> {
        val mutableLiveData = MutableLiveData<MutableList<DataStudent>>()

        CoroutineScope(Dispatchers.IO).launch {

            val studentList = mutableListOf<DataStudent>()

            fireStore.collection("Student").document(dni.toString()).get().addOnSuccessListener { student ->
                val surname = student.getString("Apellido")
                    val curso = student.getString("Curso")
                    val nota = student.getLong("Nota")?.toInt()
                    val list = DataStudent(surname!!, curso!!, nota!!.toInt())
                    studentList.add(list)
            }


//            fireStore.collection("Student")
//                .document(dni.toString())
//                .addSnapshotListener { value, error ->
//                    val surname = value?.getString("Apellido")
//                    val curso = value?.getString("Curso")
//                    val nota = value?.getString("Nota")
//                    val list = DataStudent(surname!!, curso!!, nota!!.toInt())
//                    studentList.add(list)
//                }
            mutableLiveData.postValue(studentList)

//            fireStore.collection("Student")
//                .document(dni.toString())
//                .get()
//                .addOnSuccessListener { documents ->
//                    Log.e(TAG, "${documents.id} => ${documents.data}")
//                    //documents.data?.let { studentList.add(it) }
//
//                    documents.data?.forEach { estudiante ->
//                        val student = estudiante.value
//                        studentList.add(student as DataStudent)
//                    }
//
//                    mutableLiveData.postValue(studentList)
//                }
//                .addOnFailureListener { exception ->
//                    Log.w(TAG, "Error getting documents: ", exception)
//                }
        }

        return mutableLiveData
    }

}