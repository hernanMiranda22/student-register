package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.vo.Resource

interface GradeStudentRepo {
    suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int):Resource<Int>
}