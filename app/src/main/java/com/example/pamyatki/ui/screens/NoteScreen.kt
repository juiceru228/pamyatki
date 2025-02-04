package com.example.pamyatki.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pamyatki.ui.components.Toolbar

@ExperimentalMaterial3Api
@Composable
fun NoteScreen(navController: NavHostController, theme: String, text: String) {
    Scaffold(
        topBar = {
            Toolbar(
                isOnMainScreen = false,
                navController = navController,
                OnAddItemCallback = { },
                onSaveCallback = { },
                "", "",
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Text(text = "Theme")
                Text(
                    text = theme,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Text(text = "Detail")
                Text( text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    maxLines = Int.MAX_VALUE,
                )

            }
        }
    )
}