package com.example.sistemaalumnosv2.domain

import com.example.sistemaalumnosv2.vo.Resource

interface GradeStudentUseCase {
    suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int): Resource<Int>
}