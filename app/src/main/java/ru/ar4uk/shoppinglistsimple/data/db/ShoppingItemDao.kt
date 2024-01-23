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

@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)

    @Query("SELECT * FROM shop_item WHERE shoppingListItemID = :shoppingListItemID")
    fun allItemsById(shoppingListItemID: Int): Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shop_list_item WHERE id = :shoppingListItemID")
    suspend fun listItem(shoppingListItemID: Int): ShoppingListItem

    @Update
    suspend fun insertListItem(item: ShoppingListItem)

}