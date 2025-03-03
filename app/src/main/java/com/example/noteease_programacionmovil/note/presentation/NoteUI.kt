package com.example.noteease_programacionmovil.note.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NoteScreen(viewModel: NoteViewModel,id:Int){
    LaunchedEffect(Unit) {
        viewModel.getNote(id)
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ){
        Note(viewModel)
    }
}
@Composable
fun Note(viewModel: NoteViewModel){
    val title: String by viewModel.title.observeAsState("")
    val content:String by viewModel.content.observeAsState("")
    Column {
        TitleNote(title)
        Spacer(modifier = Modifier.padding(10.dp))
        BodyNote(content)
    }
}

@Composable
fun TitleNote(title:String){
    Text(fontSize = 40.sp, fontWeight = FontWeight.Bold,text=title)
}
@Composable
fun BodyNote(content:String){
    Text(text=content)
}