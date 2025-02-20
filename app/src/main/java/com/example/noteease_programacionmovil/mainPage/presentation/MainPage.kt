package com.example.noteease_programacionmovil.mainPage.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteease_programacionmovil.core.navigation.Login
import com.example.noteease_programacionmovil.core.navigation.Register

//@Preview(showBackground = true)
@Composable
fun MainPageScreen(navController: NavController, navigateToRegister: Register,navigateToLogin: Login){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            text="NoteEasy"
        )
        Text(
            text="Bienvenido a la mejor aplicación para guardar notas"
        )
        Spacer(modifier = Modifier.height(70.dp))
        Button(
            onClick = {
                navController.navigate(navigateToRegister)
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(modifier= Modifier.padding(10.dp),fontSize = 35.sp, text="Registrarse")
        }
        Button(
            onClick = {
                navController.navigate(navigateToLogin)
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(modifier= Modifier.padding(0.dp,10.dp),fontSize = 35.sp, text="Iniciar sesión")
        }
    }
}