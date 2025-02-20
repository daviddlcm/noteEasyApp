package com.example.noteease_programacionmovil.register.data.datasource

import com.example.noteease_programacionmovil.register.data.model.CreateUserRequest
import com.example.noteease_programacionmovil.register.data.model.EmailValidateDTO
import com.example.noteease_programacionmovil.register.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RegisterService {
    @GET("user/validate-email")
    suspend fun validateEmail(@Query("email") email:String): Response<EmailValidateDTO>

    @POST("user/")
    suspend fun createUser(@Body user:CreateUserRequest):Response<UserDTO>
}