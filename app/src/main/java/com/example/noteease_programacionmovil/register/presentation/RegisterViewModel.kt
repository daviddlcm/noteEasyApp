package com.example.noteease_programacionmovil.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteease_programacionmovil.register.data.model.CreateUserRequest
import com.example.noteease_programacionmovil.register.data.repository.RegisterRepository
import com.example.noteease_programacionmovil.register.domain.CreateUserUseCase
import com.example.noteease_programacionmovil.register.domain.EmailValidateUseCase
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

class RegisterViewModel(): ViewModel(){
    private val repository = RegisterRepository()

    private var _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private var _password = MutableLiveData<String>()
    var password: LiveData<String> = _password

    private var _nameUser = MutableLiveData<String>()
    var nameUser: LiveData<String> = _nameUser

    private var _lastName = MutableLiveData<String>()
    var lastName: LiveData<String> = _lastName

    private var _success = MutableLiveData<Boolean>(false)
    val success : LiveData<Boolean> = _success

    private var _error = MutableLiveData<String>("")
    val error : LiveData<String> = _error

    private var _register = MutableLiveData<String>("")
    val register : LiveData<String> = _register


    fun onChangeEmail(Email : String) {
        _email.value = Email
    }
    fun onChangeNameUser(NameUser: String){
        _nameUser.value = NameUser
    }
    fun onChangeLastName(LastName: String){
        _lastName.value = LastName
    }
    fun onChangePassword (password : String) {
        _password.value = password
    }


        suspend fun onFocusChanged(email: String) {
            viewModelScope.launch {
                val result = EmailValidateUseCase(repository).invoke(email)
                result.onSuccess {
                        data -> (
                        if (data.success) {
                            _success.value = data.success
                            _error.value = ""
                        }
                        else
                            _error.value = "Este correo ya está registrado. Por favor, intenta con otro."
                        )
                }.onFailure {
                        exception -> _error.value = "Error al validar el correo. Inténtalo de nuevo."
                }
            }
        }
    suspend fun onClick(user: CreateUserRequest){
        val result = CreateUserUseCase().invoke(user)
        result.onSuccess {
            data ->(
                    if(data.success){
                        _register.value = "se registro correctamente token: ${data.token}"
                    }
                    else{
                        _register.value = "Ocurrio un error al registrarte"
                    }
                    )
        }.onFailure {
            exception -> _register.value = "Error en la api"
        }
    }


}