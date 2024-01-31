package ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen

import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem

sealed class ShoppingListEvent {
    data class OnShowDeleteDialog(val item: ShoppingListItem): ShoppingListEvent()
    data class OnShowEditDialog(val item: ShoppingListItem): ShoppingListEvent()
    data class OnItemClick(val item: ShoppingListItem): ShoppingListEvent()
    data object OnItemSave: ShoppingListEvent()
}