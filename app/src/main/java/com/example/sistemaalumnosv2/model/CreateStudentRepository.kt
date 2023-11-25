package com.example.sistemaalumnosv2.model

import androidx.lifecycle.LiveData

class CreateStudentRepository:CreateStudent {
    override suspend fun createStudent(
        dni: Int,
        name: String,
        surname: String,
        year: String
    ): LiveData<DataStudent> {
        TODO("Not yet implemented")
    }
}