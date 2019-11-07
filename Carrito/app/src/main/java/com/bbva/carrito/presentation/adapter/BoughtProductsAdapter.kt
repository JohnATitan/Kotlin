package com.bbva.carrito.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bbva.carrito.R
import com.bbva.carrito.data.models.Product

class BoughtProductsAdapter() : RecyclerView.Adapter<BoughtProductsAdapter.ViewHolder>() {

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
    }

    fun setProductList(selectProducts: List<Product>) {
        productList.clear()
        productList.addAll(selectProducts)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
    }
}