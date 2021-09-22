package com.example.databindingapp.models

import java.util.*

data class User(
    val name: String,
    val surname: String,
    val password: String,
    val age: Int,
    val sex: Int,
    val birthDate: Date
)