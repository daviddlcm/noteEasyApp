package com.example.noteease_programacionmovil.login.data.datasource

import com.example.noteease_programacionmovil.login.data.model.LoginUserRequest
import com.example.noteease_programacionmovil.login.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("user/signin")
    suspend fun login(@Body user : LoginUserRequest): Response<UserDTO>
}