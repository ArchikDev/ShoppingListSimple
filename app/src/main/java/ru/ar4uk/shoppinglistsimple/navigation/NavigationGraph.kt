package ru.ar4uk.shoppinglistsimple.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.AboutScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.NoteListScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.SettingsScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.ShoppingListScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    onNavigate: (String) -> Unit
) {

    NavHost(navController = navController, startDestination = Routes.SHOPPING_LIST) {
        composable(Routes.SHOPPING_LIST) {
            ShoppingListScreen() { route ->
                onNavigate(route)
            }
        }
        composable(Routes.NOTE_LIST) {
            NoteListScreen() { route ->
                onNavigate(route)
            }
        }
        composable(Routes.ABOUT) {
            AboutScreen()
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }

}