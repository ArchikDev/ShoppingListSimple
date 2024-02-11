package ru.ar4uk.shoppinglistsimple.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingNoteItem
import ru.ar4uk.shoppinglistsimple.data.model.TestItem

@Database(
    entities = [
        ShoppingListItem::class,
        ShoppingItem::class,
        ShoppingNoteItem::class,
        TestItem::class
    ],
    autoMigrations = [AutoMigration(from = 1, to = 2)],
    version = 2,
    exportSchema = true // для миграций
)
abstract class MainDb: RoomDatabase() {

    abstract fun shoppingListItemDao(): ShoppingListItemDao

    abstract fun shoppingItemDao(): ShoppingItemDao

    abstract fun shoppingNoteItemDao(): ShoppingNoteItemDao

}