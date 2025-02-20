package com.example.noteease_programacionmovil.register.data.repository

import com.example.noteease_programacionmovil.core.network.RetrofitHelper
import com.example.noteease_programacionmovil.register.data.datasource.RegisterService
import com.example.noteease_programacionmovil.register.data.model.CreateUserRequest
import com.example.noteease_programacionmovil.register.data.model.EmailValidateDTO
import com.example.noteease_programacionmovil.register.data.model.UserDTO
import retrofit2.Retrofit

class RegisterRepository {
    private val registerService = RetrofitHelper.registerService

    suspend fun validateEmail(email: String): Result<EmailValidateDTO>{
        return try{
            val response = registerService.validateEmail(email)


            if(response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception(response.errorBody()?.string() ?: "Error en la validación"))
            }
        }catch(e: Exception){
            Result.failure(e)
        }
    }
    suspend fun createUser(user:CreateUserRequest): Result<UserDTO>{
        return try{
            val response = registerService.createUser(user)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        }catch(e: Exception){
            Result.failure(e)
        }
    }
}