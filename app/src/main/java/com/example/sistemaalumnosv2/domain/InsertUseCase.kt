package com.example.sistemaalumnosv2.domain


import com.example.sistemaalumnosv2.vo.Resource

interface InsertUseCase {
    suspend fun insertStudent(dni:Int, name:String, surname:String, year:String): Resource<Int>
}