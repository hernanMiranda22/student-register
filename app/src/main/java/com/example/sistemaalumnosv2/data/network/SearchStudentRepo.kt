package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.data.DataStudent
import com.example.sistemaalumnosv2.vo.Resource

interface SearchStudentRepo {
    suspend fun searchStudent(dni:Int):Resource<MutableList<DataStudent>>
}