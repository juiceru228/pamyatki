package com.example.pamyatki.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pamyatki.ui.components.PamyatkaDbDao
import com.example.pamyatki.ui.components.PamyatkaDbEntity
import com.example.pamyatki.ui.components.Toolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalMaterial3Api
@Composable
fun NoteScreen(navController: NavHostController, id: Int, theme: String, text: String, date: String,  dao: PamyatkaDbDao) {
    var theme_local by remember { mutableStateOf(theme) }
    var text_local by remember { mutableStateOf(text) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    suspend fun  handleSaveClick(context: Context, keyboardController: SoftwareKeyboardController?){
        try {

            withContext(Dispatchers.IO) {
                dao.update(PamyatkaDbEntity(id = id, theme = theme_local, text = text_local, date = date))
            }
            withContext(Dispatchers.Main) {
                keyboardController?.hide()
            }
        } catch (e: Exception) {
            Log.e("AddNoteScreen", "Error saving note", e)
        }
    }
    Scaffold(
        topBar = {
            Toolbar(
                isOnMainScreen = false,
                navController = navController,
                OnAddItemCallback = { },
                onSaveCallback = { coroutineScope.launch {
                    handleSaveClick(context, keyboardController)
                } },
                theme_local, text_local
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Text(text = "Creator of Pamyatki: RESURRECTED")

                TextField(
                    value = theme_local,
                    onValueChange = {theme_local = it},
                    label = { Text("Theme") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 30.dp))
                TextField(
                    value = text_local,
                    onValueChange = {text_local = it},
                    label = { Text("Sample text") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    maxLines = Int.MAX_VALUE,
                    singleLine = false
                )
            }
        }
    )
}