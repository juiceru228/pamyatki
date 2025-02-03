package com.example.pamyatki.ui.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.w3c.dom.Text

@Composable
fun PamyatkiList(onItemClick: (String, String) -> Unit, AddPamyatka: AddPamyatka){
    LazyColumn( modifier = Modifier.fillMaxWidth()) {
        items(AddPamyatka.notes, key = { it.theme }) { note ->
            pamyatkaItem(note) { onItemClick(note.theme, note.text) }
        }
    }
}

fun onItemClickHandle(navController: NavHostController, theme: String, text: String){
    navController.navigate("note_info/$theme/$text")
}

@Composable
fun pamyatkaItem(note: Note, onItemClick: () -> Unit){
    Text(
        fontSize = 30.sp,
        text = note.theme,
        modifier = Modifier.padding(8.dp).fillMaxWidth().clickable { onItemClick() }
    )
}