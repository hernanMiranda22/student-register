package com.example.sistemaalumnosv2.domain.insertgradecase

import com.example.sistemaalumnosv2.vo.Resource

interface GradeStudentUseCase {
    suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int, uid : String):Resource<Int>
}