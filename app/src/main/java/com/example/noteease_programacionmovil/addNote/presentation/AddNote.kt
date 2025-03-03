package com.example.noteease_programacionmovil.addNote.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.noteease_programacionmovil.addNote.domain.VibrationUseCase
import com.example.noteease_programacionmovil.home.presentation.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun AddNoteScreen(addNoteViewModel: AddNoteViewModel,homeViewModel: HomeViewModel){
    val noteAdded by addNoteViewModel.success.observeAsState(false)
    val context = LocalContext.current
    val vibrationUseCase = VibrationUseCase(context)

    LaunchedEffect(noteAdded) {
        if (noteAdded){
            vibrationUseCase.vibrate()
            homeViewModel.refreshNotes()
            addNoteViewModel.resetNoteAddedEvent()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(35.dp),
    ){
        AddNote(modifier = Modifier.align(Alignment.Center),addNoteViewModel)
    }
}

@Composable
fun AddNote(modifier: Modifier,addNoteViewModel: AddNoteViewModel){
    val title:String by addNoteViewModel.title.observeAsState("")
    val description:String by addNoteViewModel.description.observeAsState("")

    Column(
        modifier = modifier
    ) {
        TitleAddNote()
        Spacer(modifier=Modifier.padding(15.dp))
        TitleTextField(title,{addNoteViewModel.onChangeTitle(it)})
        Spacer(modifier=Modifier.padding(10.dp))
        ContentTextField(description,{addNoteViewModel.onChangeDescription(it)})
        Spacer(modifier=Modifier.padding(20.dp))
        BottomToAdd({
            addNoteViewModel.viewModelScope.launch {
                addNoteViewModel.onClickAddNote()
            }
        })
    }
}

@Composable
fun TitleAddNote(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        text="Agregar Nota",
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TitleTextField(title:String,onChangeTitle: (String) -> Unit){
    OutlinedTextField(
        value=title,
        onValueChange = {onChangeTitle(it)},
        placeholder = {Text(text="Titulo")},
        modifier = Modifier.fillMaxWidth()
    )
}
@Composable
fun ContentTextField(description:String, onChangeDescription:(String) -> Unit){
    OutlinedTextField(
        value=description,
        onValueChange = {onChangeDescription(it)},
        placeholder = {Text(text="Contenido de la nota")},
        modifier = Modifier.fillMaxWidth(),
        maxLines = 10
    )
}
@Composable
fun BottomToAdd(onClickAddNote: ()->Unit){
    Button(
        onClick = {onClickAddNote()},
        modifier = Modifier.fillMaxWidth().height(48.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(
            text="Agregar Nota"
        )
    }
}