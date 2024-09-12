package com.example.sistemaalumnosv2.menu_screen.domain.searchstudentcase

import com.example.sistemaalumnosv2.menu_screen.data.model.DataStudent
import com.example.sistemaalumnosv2.menu_screen.data.network.searchstudent.SearchStudentRepository
import com.example.sistemaalumnosv2.menu_screen.ui.model.DataStudentUI
import com.example.sistemaalumnosv2.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchStudentUseCase @Inject constructor(private val searchStudentRepository: SearchStudentRepository) {
    suspend fun searchStudent(uid : String): MutableList<DataStudent> =
        searchStudentRepository.searchStudent(uid)

    suspend fun getAllStudents(uid: String) : Flow<List<DataStudentUI>> {

        return searchStudentRepository.getAllStudents(uid).map { items ->
            items.map {
                DataStudentUI(
                    it.dni,
                    it.name,
                    it.surname,
                    it.year,
                    it.firstTerm,
                    it.secondTerm,
                    it.thirdTerm
                )
            }
        }
    }
}

