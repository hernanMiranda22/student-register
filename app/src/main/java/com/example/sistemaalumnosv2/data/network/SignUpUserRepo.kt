package com.example.sistemaalumnosv2.data.network

import com.example.sistemaalumnosv2.vo.Resource

interface SignUpUserRepo {
    suspend fun signUpEmailAndPassword(email : String, password : String): Resource<Int>
}