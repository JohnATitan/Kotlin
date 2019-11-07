package com.bbva.carrito.data.internal.db

import androidx.room.*
import com.bbva.carrito.data.models.Product

@Dao
interface DAO {

    @Query("SELECT * FROM PRODUCT WHERE STATUS = 0")
    fun selectCartProducts(): List<Product>

    @Query("SELECT * FROM PRODUCT WHERE STATUS = 1")
    fun selectBoughtProducts(): List<Product>

    @Query("SELECT * FROM PRODUCT")
    fun selectProducts(): List<Product>

    @Insert
    fun insertProduct(product: Product)

    @Query("DELETE FROM PRODUCT  WHERE ID = :id")
    fun deleteProduct(id: Int)

    @Query("UPDATE PRODUCT SET STATUS = 1 WHERE ID = :id")
    fun updateProduct(id: Int)

}