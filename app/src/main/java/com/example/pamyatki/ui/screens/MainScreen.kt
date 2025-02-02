package com.example.pamyatki.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pamyatki.ui.components.PamyatkiList
import com.example.pamyatki.ui.components.Toolbar

@ExperimentalMaterial3Api
@Composable
fun MainScreen(isOnMainScreen: Boolean, OnBackPressedCallback: () -> Unit) {
    val items = remember { mutableStateListOf<String>() }
    Scaffold(
        topBar = {
            Toolbar(
                isOnMainScreen = isOnMainScreen,
                OnBackPressedCallback = OnBackPressedCallback,
                OnAddItemCallback = {
                    items.add("Pamyatka ${items.size + 1}")
                }
            ) },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Spacer(modifier = Modifier.padding(top = 30.dp))
                Text(text = "Welcome to Pamyatki: RESURRECTED")
                PamyatkiList(items)
            }
        }
    )
}
