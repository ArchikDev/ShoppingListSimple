package ru.ar4uk.shoppinglistsimple.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem

@Dao
interface ShoppingListItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingListItem)

    @Delete
    suspend fun deleteItem(item: ShoppingListItem)

    @Query("DELETE FROM shop_item WHERE shoppingListItemID = :listId")
    suspend fun deleteAddItems(listId: Int)

    @Query("SELECT * FROM shop_list")
    fun allItems(): Flow<List<ShoppingListItem>>

    @Transaction
    suspend fun deleteShoppingList(item: ShoppingListItem) {
        deleteAddItems(item.id!!)
        deleteItem(item)
    }

}