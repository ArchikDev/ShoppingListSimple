package ru.ar4uk.shoppinglistsimple.presentation.main_screen

import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.ShoppingListEvent

sealed class MainScreenEvent {
    data object OnItemSave: MainScreenEvent()
    data class Navigate(val route: String): MainScreenEvent()
    data class NavigateMain(val route: String): MainScreenEvent()
    data class OnNewItemClick(val route: String): MainScreenEvent()

}