package com.bbva.carrito.data.internal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bbva.carrito.data.models.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class DBConfiguration : RoomDatabase() {
    abstract fun productDAO(): DAO
}