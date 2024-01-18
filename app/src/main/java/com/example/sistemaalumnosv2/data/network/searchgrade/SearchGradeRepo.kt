package com.example.sistemaalumnosv2.data.network.searchgrade

import com.example.sistemaalumnosv2.data.model.GradeStudent
import com.example.sistemaalumnosv2.vo.Resource

interface SearchGradeRepo {

    //Funcion de la clase "SearchGradeRepoImpl"
    suspend fun getGradeStudent(dni:Int, uid: String): Resource<MutableList<GradeStudent>>
}