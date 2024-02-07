package ru.ar4uk.shoppinglistsimple.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.ar4uk.shoppinglistsimple.presentation.main_screen.MainScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.AboutScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.AddItemScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.NoteListScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.NoteNewScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.SettingsScreen
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.ShoppingListScreen

@Composable
fun MainNavigationGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.ADD_ITEM + "/{listId}") {
            AddItemScreen()
        }
        composable(Routes.NOTE_NEW) {
            NoteNewScreen() {
                navController.popBackStack()
            }
        }
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navController)
        }
    }

}