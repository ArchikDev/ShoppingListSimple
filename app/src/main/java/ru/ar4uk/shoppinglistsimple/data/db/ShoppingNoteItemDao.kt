package ru.ar4uk.shoppinglistsimple.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingNoteItem

@Dao
interface ShoppingNoteItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingNoteItem)

    @Delete
    suspend fun deleteItem(item: ShoppingNoteItem)

    @Query("SELECT * FROM shop_note_item")
    fun allNotes(): Flow<List<ShoppingNoteItem>>

    @Query("SELECT * FROM shop_note_item WHERE id = :itemID")
    suspend fun noteById(itemID: Int): ShoppingNoteItem
}