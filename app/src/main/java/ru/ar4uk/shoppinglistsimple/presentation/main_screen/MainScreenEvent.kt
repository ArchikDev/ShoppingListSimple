package ru.ar4uk.shoppinglistsimple.presentation.main_screen

import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.ShoppingListEvent

sealed class MainScreenEvent {
    data object OnShowDialog: MainScreenEvent()
    data object OnItemSave: MainScreenEvent()
}