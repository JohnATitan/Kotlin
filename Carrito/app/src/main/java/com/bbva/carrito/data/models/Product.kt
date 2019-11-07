package com.bbva.carrito.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    lateinit var name: String
    var status: Int = 0

    companion object {
        val IN_CART = 0
        val BOUGHT = 1
    }
}
