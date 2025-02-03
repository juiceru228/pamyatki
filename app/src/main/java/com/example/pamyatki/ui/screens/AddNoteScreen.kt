package com.example.pamyatki.ui.screens

import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pamyatki.ui.components.AddPamyatka
import com.example.pamyatki.ui.components.Toolbar

@ExperimentalMaterial3Api
@Composable
fun AddNoteScreen(navController: NavHostController, AddPamyatka: AddPamyatka) {
    var theme by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    fun  handleSaveClick(){
        AddPamyatka.addNote(theme, text)
        theme = ""
        text = ""
        navController.popBackStack()
    }

    Scaffold(
        topBar = {
            Toolbar(
                isOnMainScreen = false,
                navController = navController,
                OnAddItemCallback = { },
                onSaveCallback = { handleSaveClick() },
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