package com.example.sistemaalumnosv2.domain.insertstudentcase


import com.example.sistemaalumnosv2.vo.Resource

interface InsertUseCase {
    suspend fun insertStudent(dni:Int, name:String, surname:String, year:String,idUser: String): Resource<Int>
}