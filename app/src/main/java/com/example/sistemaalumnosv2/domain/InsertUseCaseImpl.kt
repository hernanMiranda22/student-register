package com.example.sistemaalumnosv2.domain

import com.example.sistemaalumnosv2.data.Student
import com.example.sistemaalumnosv2.data.network.InsertStudentRepo
import com.example.sistemaalumnosv2.vo.Resource

class InsertUseCaseImpl(private val insertStudentRepo: InsertStudentRepo):InsertUseCase {
    override suspend fun insertStudent(dni:Int, name:String, surname:String, year:String): Resource<Int> {
        return insertStudentRepo.insertStudent(dni, name, surname, year)
    }


}