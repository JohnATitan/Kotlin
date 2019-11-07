package com.bbva.carrito.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.bbva.carrito.R
import com.bbva.carrito.data.models.Product
import com.bbva.carrito.presentation.dialog.listeners.AddProductListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_add_product.*

class AddProductDialog(context: Context, private var addProductListener: AddProductListener) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_product)
        initListeners()
    }


    private fun initListeners() {
        btnAddProduct.setOnClickListener {
            if (etProductName.text.toString().isNotEmpty()) {
                val product = Product()
                product.name = etProductName.text.toString()
                product.status = Product.IN_CART
                addProductListener.onAddProductToCart(product)
                dismiss()
            } else {
                Snackbar.make(rlContainer, "Ingresa el nombre", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}