package com.example.noteease_programacionmovil.addNote.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteease_programacionmovil.addNote.data.model.CreateNoteRequest
import com.example.noteease_programacionmovil.addNote.domain.CreateNoteUseCase
import com.example.noteease_programacionmovil.addNote.domain.VibrationUseCase

class AddNoteViewModel(private val vibrationUseCase: VibrationUseCase):ViewModel() {

    private val createNoteUseCase = CreateNoteUseCase()



    private val _title = MutableLiveData<String>()
    val title :LiveData<String> = _title

    private val _description = MutableLiveData<String>()
    val description : LiveData<String> = _description

    fun onChangeTitle(title:String){
        _title.value= title
    }
    fun onChangeDescription(description:String){
        _description.value= description
    }
    private val _error = MutableLiveData<String>()
    val error :LiveData<String> = _error

    private val _success = MutableLiveData<Boolean>()
    val success :LiveData<Boolean> = _success

    fun resetNoteAddedEvent(){
        _success.value=false
    }

    suspend fun onClickAddNote(){
        val note = CreateNoteRequest(_title.value.toString(),_description.value.toString())
        val result = createNoteUseCase(note)
        result.onSuccess {
                data ->(
                if(data.success){
                    _success.value = data.success
                    vibrationUseCase.vibrate()
                    _error.value=""
                }
                else{
                    _error.value = "*No se pudo crear la nota*"
                }
                )
        }.onFailure {
                exception -> _error.value = "Ocurrio un error en la aplicacion"
        }
    }
}