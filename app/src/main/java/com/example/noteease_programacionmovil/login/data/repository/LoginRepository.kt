package com.example.noteease_programacionmovil.login.data.repository

import com.example.noteease_programacionmovil.core.network.RetrofitHelper
import com.example.noteease_programacionmovil.login.data.datasource.LoginService
import com.example.noteease_programacionmovil.login.data.model.LoginUserRequest
import com.example.noteease_programacionmovil.login.data.model.UserDTO


class LoginRepository {
    private val loginService = RetrofitHelper.loginService


    suspend fun signIn(user:LoginUserRequest): Result<UserDTO>{
        return try{
            val response = loginService.login(user)
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