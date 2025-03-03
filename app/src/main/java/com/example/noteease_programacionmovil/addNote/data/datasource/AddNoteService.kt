package com.example.noteease_programacionmovil.addNote.data.datasource

import com.example.noteease_programacionmovil.addNote.data.model.CreateNoteRequest
import com.example.noteease_programacionmovil.addNote.data.model.CreateNoteResponse
import com.example.noteease_programacionmovil.addNote.data.model.NoteDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AddNoteService {
    @POST("note")
    suspend fun addNote(@Body request: CreateNoteRequest) : Response<CreateNoteResponse>
}