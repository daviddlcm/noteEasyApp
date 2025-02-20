package com.example.noteease_programacionmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteease_programacionmovil.core.navigation.NavigationWrapper
import com.example.noteease_programacionmovil.core.network.RetrofitHelper
import com.example.noteease_programacionmovil.register.presentation.RegisterScreen
import com.example.noteease_programacionmovil.register.presentation.RegisterViewModel
import com.example.noteease_programacionmovil.ui.theme.NoteEase_programacionMovilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        RetrofitHelper.initialize(this)
        setContent {
            NavigationWrapper()
        }
    }
}

