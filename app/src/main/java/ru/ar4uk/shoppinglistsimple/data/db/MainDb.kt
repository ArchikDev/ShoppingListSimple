package ru.ar4uk.shoppinglistsimple.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingNoteItem

@Database(
    entities = [ShoppingListItem::class, ShoppingItem::class, ShoppingNoteItem::class],
    version = 1
)
abstract class MainDb: RoomDatabase() {

    abstract fun shoppingListItemDao(): ShoppingListItemDao

    abstract fun shoppingItemDao(): ShoppingItemDao

    abstract fun shoppingNoteItemDao(): ShoppingNoteItemDao

}