package ru.ar4uk.shoppinglistsimple.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
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
    autoMigrations = [AutoMigration(
        from = 3,
        to = 4,
        spec = MainDb.RenameShopList::class
    )],
    version = 4,
    exportSchema = true // для миграций
)
abstract class MainDb: RoomDatabase() {
//    @RenameColumn( // переименование полей
//        "shop_list_name",
//        fromColumnName = "time",
//        toColumnName = "my_item"
//    )
    @RenameTable.Entries(
        RenameTable(fromTableName = "shop_list_name", toTableName = "shop_list"),
//        RenameTable(fromTableName = "shop_name", toTableName = "shop_name_test")
    )
    class RenameShopList: AutoMigrationSpec
    abstract fun shoppingListItemDao(): ShoppingListItemDao

    abstract fun shoppingItemDao(): ShoppingItemDao

    abstract fun shoppingNoteItemDao(): ShoppingNoteItemDao

}