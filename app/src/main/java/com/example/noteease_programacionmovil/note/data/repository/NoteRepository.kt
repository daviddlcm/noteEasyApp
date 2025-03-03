package com.example.noteease_programacionmovil.note.data.repository

import android.util.Log
import com.example.noteease_programacionmovil.core.network.RetrofitHelper
import com.example.noteease_programacionmovil.note.data.datasource.NoteService
import com.example.noteease_programacionmovil.note.data.model.NoteResponseDTO

class NoteRepository {
    private val noteService = RetrofitHelper.getNoteById


    suspend fun getNote(request: Int):Result<NoteResponseDTO>{
        return try{
            val response = noteService.getNoteById(request)

            if(response.isSuccessful){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception(response.errorBody()?.string()))
            }
        }catch(e:Exception){
            Result.failure(e)
        }
    }
}