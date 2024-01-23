package ru.ar4uk.shoppinglistsimple.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ar4uk.shoppinglistsimple.data.db.ShoppingListItemDao
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem

class ShoppingListItemRepoImpl(
    private val dao: ShoppingListItemDao
) : ShoppingListItemRepo {
    override suspend fun insertItem(item: ShoppingListItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: ShoppingListItem) {
        dao.deleteItem(item)
    }

    override fun allItems(): Flow<List<ShoppingListItem>> {
        return dao.allItems()
    }
}