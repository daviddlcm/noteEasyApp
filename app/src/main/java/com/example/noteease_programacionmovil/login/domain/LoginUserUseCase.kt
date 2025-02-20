package com.example.noteease_programacionmovil.login.domain

import com.example.noteease_programacionmovil.login.data.model.LoginUserRequest
import com.example.noteease_programacionmovil.login.data.model.UserDTO
import com.example.noteease_programacionmovil.login.data.repository.LoginRepository

class LoginUserUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(user:LoginUserRequest) : Result<UserDTO>{
        val result = repository.signIn(user)
        return result
    }

}