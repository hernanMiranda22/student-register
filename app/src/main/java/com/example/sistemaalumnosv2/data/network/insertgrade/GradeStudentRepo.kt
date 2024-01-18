package com.example.sistemaalumnosv2.data.network.insertgrade

import com.example.sistemaalumnosv2.vo.Resource

interface GradeStudentRepo {

    //Funcion de la clase "GradeStudentRepoImpl"
    suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String):Resource<Int>
}