package com.example.sistemaalumnosv2.data.network


import com.example.sistemaalumnosv2.vo.Resource

interface InsertStudentRepo {

    //Funcion de la clase "InsertStudentRepoImpl"
    suspend fun insertStudent(dni:Int, name:String, surname:String, year:String): Resource<Int>
}