package com.example.noteease_programacionmovil.home.data.model

import java.sql.Timestamp

data class NoteDTO (
    val id_note: Int,
    val title: String,
    val content: String,
    val id_user: Int,
    val created_at: Timestamp
)