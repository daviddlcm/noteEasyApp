package com.example.noteease_programacionmovil.note.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteease_programacionmovil.note.data.repository.NoteRepository

class NoteViewModel: ViewModel() {

    private val repository = NoteRepository()

    val _title = MutableLiveData<String>()
    val title : LiveData<String> = _title

    val _content = MutableLiveData<String>()
    val content : LiveData<String> = _content

    val _error = MutableLiveData<String>()
    val error :LiveData<String> = _error

    suspend fun getNote(id: Int){

        val result = repository.getNote(id)
        result.onSuccess {
                data ->(
                if(data.success){
                    _title.value=data.note.title
                    _content.value=data.note.content
                }
                else{
                    _error.value = "*Contraseña o Correo invalido*"
                }
                )
        }.onFailure {
                exception -> _error.value = "Ocurrio un error en la aplicacion"
        }
    }
}