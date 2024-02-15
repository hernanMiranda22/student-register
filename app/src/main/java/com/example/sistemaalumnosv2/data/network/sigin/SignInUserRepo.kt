package com.example.sistemaalumnosv2.data.network.sigin

import com.example.sistemaalumnosv2.data.model.User
import com.example.sistemaalumnosv2.vo.Resource

interface SignInUserRepo {
    suspend fun signInWithEmail(email : String, password : String): Resource<User>
}