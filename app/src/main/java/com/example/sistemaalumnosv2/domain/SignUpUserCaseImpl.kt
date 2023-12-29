package com.example.sistemaalumnosv2.domain

import com.example.sistemaalumnosv2.data.network.SignUpUserRepo
import com.example.sistemaalumnosv2.vo.Resource

class SignUpUserCaseImpl(private val signUpUserRepo: SignUpUserRepo):SignUpUserUseCase {
    override suspend fun signUpEmailAndPassword(email: String, password: String): Resource<Int> =
        signUpUserRepo.signUpEmailAndPassword(email, password)
}