package com.example.noteease_programacionmovil.core.network

import android.annotation.SuppressLint
import android.content.Context
import com.example.noteease_programacionmovil.addNote.data.datasource.AddNoteService
import com.example.noteease_programacionmovil.core.dataStore.AuthInterceptor
import com.example.noteease_programacionmovil.core.dataStore.TokenDataStore
import com.example.noteease_programacionmovil.home.data.datasource.GetNotesService
import com.example.noteease_programacionmovil.login.data.datasource.LoginService
import com.example.noteease_programacionmovil.note.data.datasource.NoteService
import com.example.noteease_programacionmovil.register.data.datasource.RegisterService
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private lateinit var appContext: Context
    private val BASE_URL = "http://10.0.2.2:80/"
    //private val BASE_URL = "http://172.20.10.4:80/"

    private val tokenDataStore by lazy { TokenDataStore(appContext) }

    private val authInterceptor by lazy {
        AuthInterceptor {
            tokenDataStore.token.firstOrNull()
        }
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }



    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val registerService: RegisterService by lazy {
        retrofit.create(RegisterService::class.java)
    }
    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }
    val addNote:AddNoteService by lazy{
        retrofit.create(AddNoteService::class.java)
    }
    val getNotesService: GetNotesService by lazy {
        retrofit.create(GetNotesService::class.java)
    }
    val getNoteById: NoteService by lazy{
        retrofit.create(NoteService::class.java)
    }

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }



}