package com.example.pamyatki.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun Toolbar(isOnMainScreen: Boolean,
            navController: NavController,
            OnAddItemCallback: () -> Unit,
            onSaveCallback: () -> Unit = {},
            theme: String, text: String,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text("Pamyatki")
        },
        navigationIcon = {
            if (!isOnMainScreen) {
                IconButton(onClick = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            }
        },
        actions = {
            if (isOnMainScreen) {
                TextButton(onClick = OnAddItemCallback) {
                    Text("Add Pamyatka")
                }
                IconButton(onClick = { /* Do something */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )
                }
            } else if (theme.isNotBlank() && text.isNotBlank()) {
                TextButton(onClick = { onSaveCallback() }) {
                    Text("Save")
                }
            }
        }
    )
}
