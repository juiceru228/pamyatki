package com.example.pamyatki.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun AppNavigation() {
    val addPamyatka = remember { AddPamyatka() }
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            EnterAnimation{
                MainScreen(navController, addPamyatka)
            }

        }
        composable("add_note") {
            EnterAnimation{
                AddNoteScreen(navController, addPamyatka)
            }

        }
        composable("note_info/{theme}/{text}",
            arguments = listOf(
                navArgument("theme") { type = NavType.StringType },
                navArgument("text") { type = NavType.StringType }
        )) {backStackEntry ->
            val theme = backStackEntry.arguments?.getString("theme") ?: ""
            val text = backStackEntry.arguments?.getString("text") ?: ""
            EnterAnimation{
                NoteScreen(navController, theme, text)
            }
        }
    }
}

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        modifier = Modifier,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
    ) {
        content()
    }
}