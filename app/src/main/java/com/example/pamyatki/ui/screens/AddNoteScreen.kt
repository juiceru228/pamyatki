package com.example.pamyatki.ui.screens

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pamyatki.ui.components.PamyatkaDbDao
import com.example.pamyatki.ui.components.PamyatkaDbEntity
import com.example.pamyatki.ui.components.Toolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

@ExperimentalMaterial3Api
@Composable
fun AddNoteScreen(navController: NavHostController, dao: PamyatkaDbDao) {
    var theme by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    suspend fun  handleSaveClick(){
        try {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val currentDate = formatter.format(System.currentTimeMillis())

            withContext(Dispatchers.IO) {
                dao.insert(PamyatkaDbEntity(theme = theme, text = text, date = currentDate))
            }

            withContext(Dispatchers.Main) {
                theme = ""
                text = ""
                navController.popBackStack()
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
                    handleSaveClick()
                } },
                theme, text
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Text(text = "Creator of Pamyatki: RESURRECTED")

                TextField(
                    value = theme,
                    onValueChange = {theme = it},
                    label = { Text("Theme") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 30.dp))
                TextField(
                    value = text,
                    onValueChange = {text = it},
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