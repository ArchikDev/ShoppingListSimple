package ru.ar4uk.shoppinglistsimple.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ar4uk.shoppinglistsimple.data.db.MainDb
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingItemRepo
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingItemRepoImpl
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingListItemRepo
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingListItemRepoImpl
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingNoteItemRepo
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingNoteItemRepoImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "shop_list_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingRepoList(db: MainDb): ShoppingListItemRepo {
        return ShoppingListItemRepoImpl(db.shoppingListItemDao())
    }

    @Provides
    @Singleton
    fun provideShoppingRepoItem(db: MainDb): ShoppingItemRepo {
        return ShoppingItemRepoImpl(db.shoppingItemDao())
    }

    @Provides
    @Singleton
    fun provideShoppingRepoNote(db: MainDb): ShoppingNoteItemRepo {
        return ShoppingNoteItemRepoImpl(db.shoppingNoteItemDao())
    }

}