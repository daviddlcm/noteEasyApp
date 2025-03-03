package com.example.noteease_programacionmovil.home.data.repository

import android.util.Log
import com.example.noteease_programacionmovil.core.network.RetrofitHelper
import com.example.noteease_programacionmovil.home.data.model.NoteDTO
import com.example.noteease_programacionmovil.home.data.model.ReponseNoteDTO

class GetNotesRepository {
    private val getNotesService = RetrofitHelper.getNotesService

    suspend fun getNote(): Result<ReponseNoteDTO>{
        return try{
            val response = getNotesService.getNotes()
            Log.d("successGetNote","entro a la funcion ${response.body()}")
            if(response.isSuccessful && response.body() != null){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception(response.errorBody()?.string() ?: "Error"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}