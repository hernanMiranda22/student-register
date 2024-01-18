package com.example.sistemaalumnosv2.data.network.insertstudent


import com.example.sistemaalumnosv2.vo.Resource

interface InsertStudentRepo {

    //Funcion de la clase "InsertStudentRepoImpl"
    suspend fun insertStudent(dni:Int, name:String, surname:String, year:String, idUser: String): Resource<Int>
}