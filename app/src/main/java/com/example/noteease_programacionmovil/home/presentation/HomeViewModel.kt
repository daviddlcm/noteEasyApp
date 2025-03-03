package com.example.noteease_programacionmovil.home.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteease_programacionmovil.home.data.model.NoteDTO
import com.example.noteease_programacionmovil.home.data.model.ReponseNoteDTO
import com.example.noteease_programacionmovil.home.data.repository.GetNotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.noteease_programacionmovil.core.dataStore.TokenDataStore

class HomeViewModel(application: Application):AndroidViewModel(application) {

    private var tokenDataStore = TokenDataStore(application)

    private val repository = GetNotesRepository()
    private var _notes = MutableLiveData<List<NoteDTO>>(emptyList())
    val notes: LiveData<List<NoteDTO>> = _notes

    private var _error = MutableLiveData<String>("")
    val error:LiveData<String> = _error



    init{
        tokenDataStore = TokenDataStore(application)
        viewModelScope.launch {
            onGetNotes()
        }
    }

    fun deleteToken(){
        viewModelScope.launch {
            tokenDataStore.clearToken()
        }
    }


    suspend fun onGetNotes(){
        val result = repository.getNote()

        result.onSuccess {
                data->(
                        if(data.success){
                            _notes.value = data.notes
                        }
                        else{
                            _error.value = "no hay datos"
                        }
                        )
        }.onFailure {
            exeption -> _error.value= "Ocurrio un error"
        }
    }
    fun refreshNotes(){
        viewModelScope.launch {
            onGetNotes()
        }
    }
}