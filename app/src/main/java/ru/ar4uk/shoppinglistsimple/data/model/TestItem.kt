package ru.ar4uk.shoppinglistsimple.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_item")
data class TestItem(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String,
)
