package com.example.incorrect_warehouse_app.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.incorrect_warehouse_app.R
import com.google.android.material.textview.MaterialTextView

class ProductAdapter(private var productList: List<Product>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(productView: View): RecyclerView.ViewHolder(productView){
        val productName: MaterialTextView = productView.findViewById(R.id.productName)
        val productSize: MaterialTextView = productView.findViewById(R.id.productSize)
        val productAmount: MaterialTextView = productView.findViewById(R.id.productAmount)
        val productPrice: MaterialTextView = productView.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = productList[position].name
        holder.productSize.text = productList[position].sizeOfProduct.toString()
        holder.productAmount.text = productList[position].amount.toString()
        holder.productPrice.text = productList[position].price.toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}