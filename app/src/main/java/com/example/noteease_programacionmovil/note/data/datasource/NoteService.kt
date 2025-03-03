package com.example.noteease_programacionmovil.note.data.datasource

import com.example.noteease_programacionmovil.note.data.model.NoteResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NoteService {
    @GET("note/getById")
    suspend fun getNoteById(@Query("id_note") id:Int ):Response<NoteResponseDTO>
}