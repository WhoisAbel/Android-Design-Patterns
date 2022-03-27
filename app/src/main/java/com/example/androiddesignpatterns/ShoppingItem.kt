package com.example.androiddesignpatterns

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "item_name")
    val name: String,
    @ColumnInfo(name = "item_amount")
    val amount: Int
) {
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
}
