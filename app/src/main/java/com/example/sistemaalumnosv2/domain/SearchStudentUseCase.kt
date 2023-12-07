package com.example.sistemaalumnosv2.domain

import com.example.sistemaalumnosv2.data.DataStudent
import com.example.sistemaalumnosv2.vo.Resource

interface SearchStudentUseCase {
    suspend fun searchStudent(dni:Int): Resource<MutableList<DataStudent>>
}