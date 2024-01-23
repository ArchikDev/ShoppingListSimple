package ru.ar4uk.shoppinglistsimple.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ar4uk.shoppinglistsimple.data.db.ShoppingNoteItemDao
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingNoteItem

class ShoppingNoteItemRepoImpl(
    private val dao: ShoppingNoteItemDao
) : ShoppingNoteItemRepo {
    override suspend fun insertItem(item: ShoppingNoteItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: ShoppingNoteItem) {
        dao.deleteItem(item)
    }

    override fun allNotes(): Flow<List<ShoppingNoteItem>> {
        return dao.allNotes()
    }

    override suspend fun noteById(itemID: Int): ShoppingNoteItem {
        return dao.noteById(itemID)
    }
}