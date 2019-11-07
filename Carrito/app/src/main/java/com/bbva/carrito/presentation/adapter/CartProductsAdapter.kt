package com.bbva.carrito.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bbva.carrito.R
import com.bbva.carrito.data.models.Product
import com.bbva.carrito.presentation.adapter.listeners.CartProductsListener

class CartProductsAdapter(private val context: Context, private val cartProductsListener: CartProductsListener) : RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    private val productList: ArrayList<Product> by lazy {
        ArrayList<Product>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.adapter_product, parent, false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList.get(position)
        holder.tvProductName.text = product.name
        holder.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_shopping_basket))

        var dX = 0f
        var bool = true

        holder.ivAction.setOnClickListener {
            cartProductsListener.onBoughtProduct(product, position)
        }

        holder.itemView.setOnTouchListener { v: View, m: MotionEvent ->
            when (m.action) {
                MotionEvent.ACTION_DOWN -> {
                    dX = v.getX() - m.getRawX()
                }
                MotionEvent.ACTION_MOVE -> {
                    if ((m.getRawX() + dX) <= 0) {
                        v.animate().x(m.getRawX() + dX).setDuration(10).start()
                    }

                    if ((m.getRawX() + dX) <= -10) {
                        if (bool) {
                            bool = false
                            holder.itemView.animate().x(-100f).setDuration(50).start()
                            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                            builder.setCancelable(false)
                            builder.setMessage("¿Estas seguro de querer eliminar este producto?")
                            builder.setNegativeButton("NO") { dialogInterface, i ->
                                dialogInterface.dismiss()
                                holder.itemView.animate().x(0f).setDuration(50).start()
                                bool = true
                            }
                            builder.setPositiveButton("SI") { dialogInterface, i ->
                                dialogInterface.dismiss()
                                holder.itemView.animate().x(0f).setDuration(50).start()
                                bool = true
                                removeProduct(position)
                                cartProductsListener.onDeleteProduct(product)
                            }
                            builder.show()
                        }
                    }
                }
            }

            true
        }
    }

    fun setProduct(product: Product) {
        productList.add(product)
        notifyDataSetChanged()
    }

    fun removeProduct(index: Int) {
        productList.removeAt(index)
        notifyDataSetChanged()
    }

    fun setProductList(selectProducts: List<Product>) {
        productList.clear()
        productList.addAll(selectProducts)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        var ivAction: ImageView = itemView.findViewById(R.id.ivAction)
    }
}