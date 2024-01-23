package ru.ar4uk.shoppinglistsimple.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ar4uk.shoppinglistsimple.data.db.ShoppingItemDao
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem

class ShoppingItemRepoImpl(
    private val dao: ShoppingItemDao
) : ShoppingItemRepo {
    override suspend fun insertItem(item: ShoppingItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: ShoppingItem) {
        dao.deleteItem(item)
    }

    override fun allItemsById(shoppingListItemID: Int): Flow<List<ShoppingItem>> {
        return dao.allItemsById(shoppingListItemID)
    }

    override suspend fun listItem(shoppingListItemID: Int): ShoppingListItem {
        return dao.listItem(shoppingListItemID)
    }

    override suspend fun insertListItem(item: ShoppingListItem) {
        dao.insertListItem(item)
    }
}