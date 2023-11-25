package com.example.sistemaalumnosv2.model

import retrofit2.Call
import retrofit2.http.POST

interface ApiService {
    @POST
    suspend fun insertDataStudent(dataStudent: DataStudent): Call<DataStudent>
}