package com.bbva.carrito.presentation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bbva.carrito.R
import com.bbva.carrito.data.models.Product
import com.bbva.carrito.presentation.adapter.BoughtProductsAdapter
import com.bbva.carrito.presentation.adapter.CartProductsAdapter
import com.bbva.carrito.presentation.adapter.listeners.CartProductsListener
import com.bbva.carrito.presentation.dialog.AddProductDialog
import com.bbva.carrito.presentation.dialog.listeners.AddProductListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AddProductListener, CartProductsListener {

    private val cartProductsAdapter by lazy {
        CartProductsAdapter(this, this)
    }

    private val boughtProductsAdapter by lazy {
        BoughtProductsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        rvCartProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCartProducts.adapter = cartProductsAdapter

        rvBoughtProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvBoughtProducts.adapter = boughtProductsAdapter

        ivAddProduct.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ivAddProduct -> AddProductDialog(this, this).show()
        }
    }

    override fun onAddProductToCart(product: Product) {
        cartProductsAdapter.setProduct(product)
    }

    override fun onBoughtProduct(product: Product, index: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("¿Estas seguro de querer comprar este producto?")
        builder.setCancelable(false)
        builder.setNegativeButton("NO") { dialogInterface, i ->
            dialogInterface.dismiss()
        }
        builder.setPositiveButton("SI") { dialogInterface, i ->
            boughtProductsAdapter.setProduct(product)
            cartProductsAdapter.removeProduct(index)
        }
        builder.show()
    }
}
