package com.example.noteease_programacionmovil.register.data.model

data class CreateUserRequest (
    val name: String,
    val lastName:String,
    val email: String,
    val password: String
)