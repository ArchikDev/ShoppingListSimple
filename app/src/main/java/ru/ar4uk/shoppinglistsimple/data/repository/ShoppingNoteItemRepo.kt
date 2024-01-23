package ru.ar4uk.shoppinglistsimple.data.repository

import kotlinx.coroutines.flow.Flow
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingNoteItem

interface ShoppingNoteItemRepo {
    suspend fun insertItem(item: ShoppingNoteItem)

    suspend fun deleteItem(item: ShoppingNoteItem)

    fun allNotes(): Flow<List<ShoppingNoteItem>>

    suspend fun noteById(itemID: Int): ShoppingNoteItem
}