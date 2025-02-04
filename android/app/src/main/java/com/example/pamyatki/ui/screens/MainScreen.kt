package com.example.pamyatki.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pamyatki.ui.components.PamyatkiDatabase
import com.example.pamyatki.ui.components.PamyatkiList
import com.example.pamyatki.ui.components.Toolbar
import com.example.pamyatki.ui.components.onItemClickHandle

@ExperimentalMaterial3Api
@Composable
fun MainScreen(navController: NavHostController, database: PamyatkiDatabase) {
    Scaffold(
        topBar = {
            Toolbar(
                isOnMainScreen = true,
                navController = navController,
                OnAddItemCallback = { navController.navigate("add_note") },
                { }, "", ""
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Spacer(modifier = Modifier.padding(top = 15.dp))
                PamyatkiList(onItemClick = { id, theme, text, date ->
                    onItemClickHandle(navController, id, theme, text, date)
                }, database.getDao())
            }
        }
    )
}