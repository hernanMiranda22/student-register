package com.example.sistemaalumnosv2.domain.signupcase

import com.example.sistemaalumnosv2.data.network.signupemail.SignUpUserRepo
import com.example.sistemaalumnosv2.vo.Resource

class SignUpUserCaseImpl(private val signUpUserRepo: SignUpUserRepo): SignUpUserUseCase {
    override suspend fun signUpEmailAndPassword(email: String, password: String): Resource<Int> =
        signUpUserRepo.signUpEmailAndPassword(email, password)
}