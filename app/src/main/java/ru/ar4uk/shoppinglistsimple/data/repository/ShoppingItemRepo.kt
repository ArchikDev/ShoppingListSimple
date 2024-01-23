package ru.ar4uk.shoppinglistsimple.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem

interface ShoppingItemRepo {
    suspend fun insertItem(item: ShoppingItem)

    suspend fun deleteItem(item: ShoppingItem)

    fun allItemsById(shoppingListItemID: Int): Flow<List<ShoppingItem>>

    suspend fun listItem(shoppingListItemID: Int): ShoppingListItem

    suspend fun insertListItem(item: ShoppingListItem)
}