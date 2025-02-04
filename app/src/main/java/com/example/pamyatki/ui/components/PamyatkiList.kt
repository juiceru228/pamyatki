package com.example.pamyatki.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun PamyatkiList(onItemClick: (String, String) -> Unit, dao: PamyatkaDbDao){
    val pamyatki = dao.getAllPamyatki().collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    var selectedPamyatka = remember { mutableStateOf<PamyatkaDbEntity?>(null) }
    LazyColumn( modifier = Modifier.fillMaxWidth()) {
        items(pamyatki.value, key = { it.theme }) { pamyatka ->
            Row {
                Text(
                    fontSize = 30.sp,
                    text = pamyatka.theme,
                    modifier = Modifier.padding(8.dp).weight(1f).clickable { onItemClick(pamyatka.theme, pamyatka.text) }
                )
                TextButton(onClick = {selectedPamyatka.value = pamyatka}) {
                    Text("Remove")
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

fun onItemClickHandle(navController: NavHostController, theme: String, text: String){
    navController.navigate("note_info/$theme/$text")
}


