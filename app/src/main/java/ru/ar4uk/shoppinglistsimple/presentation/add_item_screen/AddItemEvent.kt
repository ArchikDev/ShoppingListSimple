package ru.ar4uk.shoppinglistsimple.presentation.add_item_screen

import ru.ar4uk.shoppinglistsimple.data.model.ShoppingItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogEvent
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.ShoppingListEvent

sealed class AddItemEvent {
    data class OnDelete(val item: ShoppingItem): AddItemEvent()
    data class OnShowEditDialog(val item: ShoppingItem): AddItemEvent()
    data class OnTextChange(val text: String): AddItemEvent()
    data class OnCheckedChange(val item: ShoppingItem): AddItemEvent()
    data object OnItemSave: AddItemEvent()
}