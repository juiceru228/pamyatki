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
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun Toolbar(isOnMainScreen: Boolean,
            OnBackPressedCallback: () -> Unit,
            OnAddItemCallback: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("Pamyatki")
        },
        navigationIcon = {
            IconButton(onClick = {
                if (isOnMainScreen){
                    OnBackPressedCallback()
                }
                else{
                    /*nothing*/
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
        actions = {
            TextButton(onClick = OnAddItemCallback) {
                Text("Add Pamyatka")
            }
            IconButton(onClick = {/* do something */}) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "do something"
                )
            }
        }
    )
}
