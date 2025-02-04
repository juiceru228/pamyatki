package com.example.pamyatki.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun PamyatkiList(onItemClick: (Int, String, String, String) -> Unit, dao: PamyatkaDbDao){
    val pamyatki = dao.getAllPamyatki().collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()
    var selectedPamyatka = remember { mutableStateOf<PamyatkaDbEntity?>(null) }
    LazyColumn( modifier = Modifier.fillMaxWidth()) {
        items(pamyatki.value, key = { it.theme }) { pamyatka ->
            Box(modifier = Modifier.clickable { onItemClick(pamyatka.id, pamyatka.theme, pamyatka.text, pamyatka.date) }.fillMaxWidth())
            {

                Row() {
                    Column( modifier = Modifier.weight(1f) ){
                        Text(
                            fontSize = 30.sp,
                            text = pamyatka.theme,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            fontSize = 10.sp,
                            text = pamyatka.date,
                            modifier = Modifier.padding(8.dp),
                            color = Color.White.copy(alpha = 0.5f)
                        )
                    }

                    TextButton(
                        onClick = { selectedPamyatka.value = pamyatka }
                    ) {
                        Text("Remove")
                    }
                }
            }
        }
    }
    selectedPamyatka.value?.let { pamyatka ->
        AlertDialog(
            onDismissRequest = {
                selectedPamyatka.value = null
            },
            title = {
                Text(text = "Confirmation")
            },
            text = {
                Text("Are you sure you want to delete this item?")
            },
            confirmButton = {
                Button(onClick = {
                    coroutineScope.launch {
                        dao.delete(pamyatka)
                        selectedPamyatka.value = null
                    }
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = {
                    selectedPamyatka.value = null
                }) {
                    Text("Dismiss")
                }
            }
        )
    }
}

fun onItemClickHandle(navController: NavHostController, id: Int, theme: String, text: String, date: String){
    navController.navigate("note_info/$id/$theme/$text/$date")
}


