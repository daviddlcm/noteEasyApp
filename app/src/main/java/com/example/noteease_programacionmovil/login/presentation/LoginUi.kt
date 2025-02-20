package com.example.noteease_programacionmovil.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.noteease_programacionmovil.R
import com.example.noteease_programacionmovil.core.navigation.Home
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(),navController: NavController,navigateToHome: Home) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel,navController,navigateToHome)
    }
}

@Composable
fun Login(modifier: Modifier,viewModel: LoginViewModel,navController: NavController,navigateToHome: Home) {
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val coroutineScope = rememberCoroutineScope()
    val error: String by viewModel.error.observeAsState("")
    val success: Boolean by viewModel.success.observeAsState(false)

    LaunchedEffect(success) {
        if(success){
            navController.navigate(navigateToHome)
        }
    }

    Column(modifier = modifier) {
        HeaderImage(Modifier.align((Alignment.CenterHorizontally)))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(email,{viewModel.onChangeEmail(it)})
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField(password,{viewModel.onChangePassword(it)})
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton({
            coroutineScope.launch {
                viewModel.onLoginSelected()
            }
        })
        Spacer(modifier.padding(10.dp))
        MessageError(error)
    }
}

@Composable
fun EmailField(email:String,onTextFieldChanged: (String) -> Unit) {

    TextField(value = email, onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text ="Correo electronico")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordField(password:String,onTextFieldChanged: (String) -> Unit){
    TextField(value = password, onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text ="Contraseña")},
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}
@Composable
fun LoginButton(onLoginSelected : () -> Unit){
    Button(
        onClick = {onLoginSelected()},
        modifier = Modifier.fillMaxWidth().height(48.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text="Iniciar Sesión"
        )
    }
}
@Composable
fun MessageError(error:String){
    Text(text=error,color= Color.Red,modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.noteesasylogo),
        contentDescription = "HeaderImage",
        modifier= modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun ShowPreview(){
//    LoginScreen(viewModel())
//}