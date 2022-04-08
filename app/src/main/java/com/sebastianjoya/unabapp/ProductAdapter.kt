package com.sebastianjoya.unabapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sebastianjoya.unabapp.databinding.ProductItemBinding

class ProductAdapter(private var products:ArrayList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    fun refresh(myProducts: ArrayList<Product>){
        products=myProducts
        notifyDataSetChanged()
    }


    class ProductViewHolder(private val binding: ProductItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(myProduct:Product){
            binding.product = myProduct
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding = DataBindingUtil.inflate(
            inflate,
            R.layout.product_item,
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}