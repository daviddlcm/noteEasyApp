package com.example.noteease_programacionmovil.addNote.data.repository

import android.util.Log
import com.example.noteease_programacionmovil.addNote.data.model.CreateNoteRequest
import com.example.noteease_programacionmovil.addNote.data.model.CreateNoteResponse
import com.example.noteease_programacionmovil.addNote.data.model.NoteDTO
import com.example.noteease_programacionmovil.core.network.RetrofitHelper
import retrofit2.Response

class AddNoteRepository {
    private val addNoteService = RetrofitHelper.addNote

    suspend fun createNote(request: CreateNoteRequest): Result<CreateNoteResponse> {
        return try{
            val response = addNoteService.addNote(request)
            if(response.isSuccessful){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception(response.errorBody()?.string()))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
    }
}