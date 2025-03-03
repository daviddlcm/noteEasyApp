package com.example.noteease_programacionmovil.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteease_programacionmovil.home.presentation.HomeScreen
import com.example.noteease_programacionmovil.mainPage.presentation.MainPageScreen
import com.example.noteease_programacionmovil.login.presentation.LoginScreen
import com.example.noteease_programacionmovil.note.presentation.NoteScreen
import com.example.noteease_programacionmovil.register.presentation.RegisterScreen

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainPage){
        composable<Register> { RegisterScreen(viewModel()) }
        composable<MainPage> { MainPageScreen(navController,Register,Login) }
        composable<Login>{ LoginScreen(viewModel(),navController,Home) }
        composable<Home>{ HomeScreen(viewModel(),navController,MainPage) }
        composable(
            route= "note/{id}",
        ){  backStackEntry ->
            val nodeId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if(nodeId != null){
                NoteScreen(viewModel(),nodeId)
            }
        }
    }
}