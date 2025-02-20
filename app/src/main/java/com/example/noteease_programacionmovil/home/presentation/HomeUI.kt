package com.example.noteease_programacionmovil.home.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(top = 15.dp), topBar = {
        TopAppBar(title = { Text(text = "MIS NOTAS") },
            actions = {
                var isMenuOpened by remember { mutableStateOf(false) }
                IconButton(onClick = { isMenuOpened = true }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Opciones de mane"
                    )
                    NewMenu(isExpanded = isMenuOpened, onItemClick = { item ->
                        Log.i("CursosANTAG", "Elemento seleccionado: $item")

                    }) {
                        isMenuOpened = false
                    }
                }
            })
    }) { innerPadding ->
        Home(modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun Home(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xfff0f0f0))
    ) {
        CardNote()
    }
}

@Composable
fun CardNote() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(color = Color.White,shape= RoundedCornerShape(16.dp))
            .height(200.dp)
            .border(2.dp,Color.White,shape= RoundedCornerShape(16.dp)),
        
        ) {
        //Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "que pasas cuano el titulo es grande y si sigue que mas a donde va", fontSize = 30.sp,modifier = Modifier.padding(20.dp).height(70.dp))
        //Spacer(modifier=Modifier.padding(10.dp))
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text="description aquie por ejemplo que pueden ir mas cosas del texto se tiene que cortar",modifier = Modifier.padding(20.dp).weight(1f))
            Text(text="08/02/2025 12:03",modifier = Modifier.padding(20.dp).weight(1f), )
        }
    }

}


@Composable
fun NewMenu(isExpanded: Boolean, onItemClick: (String) -> Unit, onDismiss: () -> Unit) {
    val items = listOf("Cerrar Sesión")
    DropdownMenu(expanded = isExpanded, onDismissRequest = onDismiss) {
        items.forEach { item ->
            DropdownMenuItem(text = {
                Text(text = item)
            }, onClick = {
                onItemClick(item)
                onDismiss()
            })
        }
    }
}