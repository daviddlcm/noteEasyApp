package com.example.noteease_programacionmovil.login.presentation

import android.app.Application
import android.util.Log
import androidx.datastore.dataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteease_programacionmovil.core.dataStore.TokenDataStore
import com.example.noteease_programacionmovil.login.data.model.LoginUserRequest
import com.example.noteease_programacionmovil.login.data.repository.LoginRepository
import com.example.noteease_programacionmovil.login.domain.LoginUserUseCase
import kotlinx.coroutines.launch
import androidx.lifecycle.AndroidViewModel

class LoginViewModel(application: Application): AndroidViewModel(application) {

    private val tokenDataStore: TokenDataStore

    init{
        tokenDataStore = TokenDataStore(application)
    }


    private var _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private var _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    private var _error = MutableLiveData<String>()
    var error : LiveData<String> = _error

    private var _savedToken = MutableLiveData<String>()
    var savedToken: LiveData<String> = _savedToken

    private var _success = MutableLiveData<Boolean>()
    var success: LiveData<Boolean> = _success

    fun onChangeEmail(Email:String) {
        _email.value = Email
    }
    fun onChangePassword(Password : String){
        _password.value = Password
    }
    fun saveToken(token:String){
        viewModelScope.launch {
            tokenDataStore.save(token)
        }
    }

    private suspend fun verifyToken(){
        tokenDataStore.token.collect{ savedToken ->
            Log.d("TokenVerification", "Token guardado: $savedToken")
        }
    }



    suspend fun onLoginSelected(){
        val user = LoginUserRequest(_email.value.toString(),_password.value.toString())
        val result = LoginUserUseCase().invoke(user)
        result.onSuccess {
                data ->(
                if(data.success){
                    //_error.value = "se registro correctamente token: ${data.token}"
                    saveToken(data.token)
                    //verifyToken()
                    _success.value = true
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