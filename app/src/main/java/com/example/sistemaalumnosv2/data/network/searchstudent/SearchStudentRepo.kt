package com.example.sistemaalumnosv2.data.network.searchstudent

import com.example.sistemaalumnosv2.data.model.DataStudent
import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.vo.Resource

interface SearchStudentRepo {

    //Funcion de la clase "SearchStudentRepoImpl"
    suspend fun searchStudent(uid: String):Resource<MutableList<DataStudent>>
}