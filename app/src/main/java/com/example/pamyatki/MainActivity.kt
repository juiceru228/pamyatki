package com.example.pamyatki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.pamyatki.ui.components.AppNavigation
import com.example.pamyatki.ui.components.BackPressHandler
import com.example.pamyatki.ui.screens.MainScreen
import com.example.pamyatki.ui.theme.PamyatkiTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val backPressHandler = BackPressHandler(onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                moveTaskToBack(true)
            }
        })
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressHandler.handleBackPress(this@MainActivity)
            }
        })
        setContent {
            var isOnMainScreen by remember { mutableStateOf(true) }
            PamyatkiTheme {
                AppNavigation()
            }
        }
    }
}