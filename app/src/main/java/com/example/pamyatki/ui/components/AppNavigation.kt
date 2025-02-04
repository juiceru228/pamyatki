package com.example.pamyatki.ui.components


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pamyatki.ui.screens.AddNoteScreen
import com.example.pamyatki.ui.screens.MainScreen
import com.example.pamyatki.ui.screens.NoteScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(database: PamyatkiDatabase) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(navController, database)
        }
        composable("add_note") {
            AddNoteScreen(navController, database.getDao())
        }
        composable("note_info/{theme}/{text}",
            arguments = listOf(
                navArgument("theme") { type = NavType.StringType },
                navArgument("text") { type = NavType.StringType }
            )) {backStackEntry ->
            val theme = backStackEntry.arguments?.getString("theme") ?: ""
            val text = backStackEntry.arguments?.getString("text") ?: ""
            NoteScreen(navController, theme, text)
        }
    }
}