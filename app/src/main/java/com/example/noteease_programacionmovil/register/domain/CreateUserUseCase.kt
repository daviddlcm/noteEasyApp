package com.example.noteease_programacionmovil.register.domain

import com.example.noteease_programacionmovil.register.data.model.CreateUserRequest
import com.example.noteease_programacionmovil.register.data.model.UserDTO
import com.example.noteease_programacionmovil.register.data.repository.RegisterRepository

class CreateUserUseCase {
    private val repository = RegisterRepository()

    suspend operator fun invoke(user:CreateUserRequest): Result<UserDTO>{
        val result = repository.createUser(user)

        return result
    }
}