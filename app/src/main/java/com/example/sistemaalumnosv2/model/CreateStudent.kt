package com.example.sistemaalumnosv2.model

import androidx.lifecycle.LiveData

interface CreateStudent {
    suspend fun createStudent(dni:Int, name:String, surname:String, year:String):LiveData<DataStudent>
}