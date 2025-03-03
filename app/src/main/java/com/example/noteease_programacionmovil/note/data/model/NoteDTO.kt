package com.example.noteease_programacionmovil.note.data.model

import java.sql.Timestamp

data class NoteDTO(
    val id_note:Int,
    val title:String,
    val content:String,
    val created_at:Timestamp
)
