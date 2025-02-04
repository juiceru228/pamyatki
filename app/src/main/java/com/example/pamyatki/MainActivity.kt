package com.example.pamyatki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.room.Room
import com.example.pamyatki.ui.components.AppNavigation
import com.example.pamyatki.ui.components.PamyatkiDatabase
import com.example.pamyatki.ui.theme.PamyatkiTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val db: PamyatkiDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            PamyatkiDatabase::class.java, "pamyatki.db"
        ).fallbackToDestructiveMigration().build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PamyatkiTheme {
                AppNavigation(db)
            }
        }
    }
}