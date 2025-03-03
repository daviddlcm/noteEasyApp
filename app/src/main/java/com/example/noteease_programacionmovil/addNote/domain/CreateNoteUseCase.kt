package com.example.noteease_programacionmovil.addNote.domain

import com.example.noteease_programacionmovil.addNote.data.model.CreateNoteRequest
import com.example.noteease_programacionmovil.addNote.data.model.CreateNoteResponse
import com.example.noteease_programacionmovil.addNote.data.model.NoteDTO
import com.example.noteease_programacionmovil.addNote.data.repository.AddNoteRepository

class CreateNoteUseCase {
    private val repository = AddNoteRepository()

    suspend operator fun invoke(note:CreateNoteRequest): Result<CreateNoteResponse>{
        val result = repository.createNote(note)
        return result
    }
}