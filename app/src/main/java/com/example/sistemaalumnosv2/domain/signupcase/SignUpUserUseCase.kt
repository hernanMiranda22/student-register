package com.example.sistemaalumnosv2.domain.signupcase

import com.example.sistemaalumnosv2.vo.Resource

interface SignUpUserUseCase {
    suspend fun signUpEmailAndPassword(email : String, password : String): Resource<Int>
}