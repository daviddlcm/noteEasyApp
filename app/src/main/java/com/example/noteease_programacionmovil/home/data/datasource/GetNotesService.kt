package com.example.noteease_programacionmovil.home.data.datasource

import com.example.noteease_programacionmovil.home.data.model.NoteDTO
import com.example.noteease_programacionmovil.home.data.model.ReponseNoteDTO
import retrofit2.Response
import retrofit2.http.GET

interface GetNotesService {
    @GET("note")
    suspend fun getNotes():Response<ReponseNoteDTO>
}