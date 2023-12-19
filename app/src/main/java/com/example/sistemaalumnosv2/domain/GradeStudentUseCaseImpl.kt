package com.example.sistemaalumnosv2.domain

import com.example.sistemaalumnosv2.data.network.GradeStudentRepo
import com.example.sistemaalumnosv2.vo.Resource

class GradeStudentUseCaseImpl(private val gradeStudentRepo: GradeStudentRepo):GradeStudentUseCase {
    override suspend fun insertGrade(dni:Int, firstTerm:Int,secondTerm:Int,thirdTerm:Int): Resource<Int> =
        gradeStudentRepo.insertGrade(dni, firstTerm, secondTerm, thirdTerm)
}