package com.example.noteease_programacionmovil.register.domain

import com.example.noteease_programacionmovil.register.data.model.EmailValidateDTO
import com.example.noteease_programacionmovil.register.data.repository.RegisterRepository

class EmailValidateUseCase(private val repository: RegisterRepository){
    private val reposotory = RegisterRepository()


    suspend operator fun invoke(email: String): Result<EmailValidateDTO>{
        return reposotory.validateEmail(email)
    }
}