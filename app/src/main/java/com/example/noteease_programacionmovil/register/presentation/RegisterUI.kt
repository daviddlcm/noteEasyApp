package com.example.noteease_programacionmovil.register.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.noteease_programacionmovil.core.navigation.MainPage
import com.example.noteease_programacionmovil.register.data.model.CreateUserRequest
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(viewModel: RegisterViewModel,navController: NavController,nagivateToMainPage:MainPage) {
    val email: String by viewModel.email.observeAsState("")
    val name: String by viewModel.nameUser.observeAsState("")
    val lastName: String by viewModel.lastName.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val success: Boolean by viewModel.success.observeAsState(false)
    val error: String by viewModel.error.observeAsState("")
    val register: String by viewModel.register.observeAsState("")
    var isPasswordVisible by remember { mutableStateOf(false) }
    val successRegister by viewModel.successRegister.observeAsState(false)

    LaunchedEffect(successRegister) {
        if(successRegister){
            navController.navigate(nagivateToMainPage)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Registrate",
            fontSize = 45.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.onChangeNameUser(it) },
            label = { Text("Ingresa tu nombre") },
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { viewModel.onChangeLastName(it) },
            label = { Text("Ingresa tu apellido") },
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onChangeEmail(it) },
            label = { Text("Ingresa tu correo electronico") },
            modifier = Modifier
                .padding(start = 20.dp,end = 20.dp, top= 10.dp, bottom = 5.dp)
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    viewModel.viewModelScope.launch {
                        if (!focusState.isFocused && email.isNotEmpty()) {
                            Log.e("data", "Ingreso")
                            viewModel.onFocusChanged(email)
                        }
                    }
                },
            shape = RoundedCornerShape(5.dp)
        )
        Text(
            modifier = Modifier.padding(start = 35.dp),
            text = error,
            color = Color.Red
        )
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onChangePassword(it) },
            label = { Text("Ingresa tu contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(start = 20.dp,end = 20.dp,bottom = 10.dp, top = 5.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val user = CreateUserRequest(name, lastName, email, password)
                    viewModel.viewModelScope.launch {
                        viewModel.onClick(user)
                    }
                },
                enabled = success,
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    fontSize = 20.sp,
                    text = "Registrarse",
                    modifier = Modifier.padding(10.dp, 5.dp)
                )
            }
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    RegisterScreen(viewModel = RegisterViewModel())
//}