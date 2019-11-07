package com.bbva.carrito.domain.presenters

import android.content.Context
import androidx.room.Room
import com.bbva.carrito.data.internal.db.DBConfiguration
import com.bbva.carrito.data.models.Product
import com.bbva.carrito.presentation.activities.views.MainView

class MainPresenter(private val context: Context, val mainView: MainView) {

    private val db by lazy {
        Room.databaseBuilder(context, DBConfiguration::class.java, "products").allowMainThreadQueries().build()
    }

    fun insertProduct(product: Product) {
        db.productDAO().insertProduct(product)
    }

    fun selectCartProducts() {
        mainView.showCartProducts(db.productDAO().selectCartProducts())

    }

    fun selectBoughtProducts() {
        mainView.showBoughtProducts(db.productDAO().selectBoughtProducts())
    }

    fun deleteProduct(product: Product) {
        db.productDAO().deleteProduct(product.id)
    }

    fun updateProduct(product: Product) {
        db.productDAO().updateProduct(product.id)
    }


}