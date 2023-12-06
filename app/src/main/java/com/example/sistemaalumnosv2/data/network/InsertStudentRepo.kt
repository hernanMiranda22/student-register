package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.data.Student
import com.example.sistemaalumnosv2.vo.Resource

interface InsertStudentRepo {
    suspend fun insertStudent(dni:Int, name:String, surname:String, year:String): Resource<Int>
}