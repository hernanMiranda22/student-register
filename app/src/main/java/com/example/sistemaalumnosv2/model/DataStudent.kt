package com.example.sistemaalumnosv2.model

import com.google.gson.annotations.SerializedName

data class DataStudent(
    @SerializedName("DNI") val dni:Int,
    @SerializedName("Nombre") val name:String,
    @SerializedName("Apellido") val surname:String,
    @SerializedName("Curso") val year:String,
)
