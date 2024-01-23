package ru.ar4uk.shoppinglistsimple.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_note_item")
data class ShoppingNoteItem(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String,
    val time: String
)
