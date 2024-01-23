package ru.ar4uk.shoppinglistsimple.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_item")
data class ShoppingItem(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val isCheck: Boolean,
    val shoppingListItemID: Int
)
