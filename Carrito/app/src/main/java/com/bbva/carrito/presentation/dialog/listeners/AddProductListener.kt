package com.bbva.carrito.presentation.dialog.listeners

import com.bbva.carrito.data.models.Product

interface AddProductListener {

    fun onAddProductToCart(product: Product)
}