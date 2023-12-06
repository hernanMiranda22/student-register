package com.example.sistemaalumnosv2.model

import androidx.lifecycle.LiveData
import com.example.sistemaalumnosv2.data.DataStudent

interface SearchStudent {

    suspend fun searchStudent(dni:Int):LiveData<MutableList<DataStudent>>
}