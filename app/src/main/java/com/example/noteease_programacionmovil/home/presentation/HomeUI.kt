package com.example.noteease_programacionmovil.home.presentation

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.navigation.NavController
import com.example.noteease_programacionmovil.addNote.presentation.AddNoteScreen
import com.example.noteease_programacionmovil.addNote.presentation.AddNoteViewModel
import com.example.noteease_programacionmovil.core.navigation.MainPage
import com.example.noteease_programacionmovil.core.navigation.Note
import com.example.noteease_programacionmovil.home.data.model.NoteDTO
import java.sql.Timestamp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel,navController: NavController,navigateToMainPage:MainPage) {
    //val error: String by viewModel.error.observeAsState("")
    val notes : List<NoteDTO> by viewModel.notes.observeAsState(emptyList())

    val navItemList = listOf(
        NavItem("Home",Icons.Default.Home),
        NavItem("Add Notes",Icons.Default.AddCircle)
    )
    var selectedIndex by remember{
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {
        viewModel.onGetNotes()
    }
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(top = 15.dp), topBar = {
        TopAppBar(title = { Text(text = "MIS NOTAS") },
            actions = {
                var isMenuOpened by remember { mutableStateOf(false) }
                IconButton(onClick = { isMenuOpened = true }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Opciones de menu"
                    )
                    NewMenu(isExpanded = isMenuOpened, onItemClick = { item ->
                        Log.i("CursosANTAG", "Elemento seleccionado: $item")
                        if(item == "Cerrar Sesión"){
                            viewModel.deleteToken()
                            navController.navigate(navigateToMainPage)
                        }
                    }) {
                        isMenuOpened = false
                    }
                }
            })
    },
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon ={
                            Icon(imageVector = navItem.icon, contentDescription = "Icon")
                        },
                        label ={
                            Text(text=navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Home(modifier = Modifier.padding(innerPadding),notes,selectedIndex,viewModel,navController)
    }

}


@Composable
fun Home(modifier: Modifier,notes: List<NoteDTO>,selectedIndex:Int,homeViewModel: HomeViewModel,navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xfff0f0f0))
    ) {
        when(selectedIndex){
            0->LazyColumn {
                items (notes){ item ->
                    CardNote(item.id_note,item.title,item.content,item.created_at,navController)
                }
            }
            1-> AddNoteScreen(AddNoteViewModel(),homeViewModel)
        }
    }
}

@Composable
fun CardNote(id_note:Int, title:String,content:String,created_at:Timestamp,navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(color = Color.White,shape= RoundedCornerShape(16.dp))
            .height(200.dp)
            .border(2.dp,Color.White,shape= RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate("note/$id_note")
            },

        ) {
        //Spacer(modifier = Modifier.padding(10.dp))
        Text(text = title, fontSize = 30.sp,modifier = Modifier.padding(20.dp).height(70.dp))
        //Spacer(modifier=Modifier.padding(10.dp))
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text=content,modifier = Modifier.padding(20.dp).weight(1f))
            Text(text="${created_at}",modifier = Modifier.padding(20.dp).weight(1f), )
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