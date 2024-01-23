package ru.ar4uk.shoppinglistsimple.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem

interface ShoppingListItemRepo {

    suspend fun insertItem(item: ShoppingListItem)

    suspend fun deleteItem(item: ShoppingListItem)

    fun allItems(): Flow<List<ShoppingListItem>>
}