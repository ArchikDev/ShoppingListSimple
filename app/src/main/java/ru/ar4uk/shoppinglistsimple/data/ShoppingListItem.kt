package ru.ar4uk.shoppinglistsimple.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_list_item")
data class ShoppingListItem(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val isCheck: Boolean,
    val shoppingListNameID: Int
)
