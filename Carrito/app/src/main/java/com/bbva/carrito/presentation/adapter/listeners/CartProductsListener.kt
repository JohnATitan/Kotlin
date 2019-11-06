package com.bbva.carrito.presentation.adapter.listeners

import com.bbva.carrito.data.models.Product

interface CartProductsListener {

    fun onBoughtProduct(product: Product, index: Int)
}