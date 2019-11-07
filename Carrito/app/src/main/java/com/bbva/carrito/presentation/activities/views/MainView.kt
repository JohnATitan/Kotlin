package com.bbva.carrito.presentation.activities.views

import com.bbva.carrito.data.models.Product

interface MainView {
    fun showCartProducts(productList: List<Product>)
    fun showBoughtProducts(productList: List<Product>)
}