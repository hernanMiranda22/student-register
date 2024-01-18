package com.example.sistemaalumnosv2.domain.signincase

import com.example.sistemaalumnosv2.data.model.User
import com.example.sistemaalumnosv2.vo.Resource

interface SignInUseCase {
    suspend fun signInWithEmail(email : String, password : String): Resource<User>
}