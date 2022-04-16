package com.sebastianjoya.unabapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebastianjoya.unabapp.model.Product
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ProductItemBinding

class ProductAdapter(private var products:ArrayList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var onItemClickListener:((Product)->Unit)?=null

    fun refresh(myProducts: ArrayList<Product>){
        products=myProducts
        notifyDataSetChanged()
    }


    class ProductViewHolder(private val binding: ProductItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(myProduct: Product, onItemClickListener: ((Product) -> Unit)?){
            binding.product = myProduct

            /**
             * Voy a poner un escuchador para que cuando de click sobre un producto, ejecute una
             * función
             */

            binding.root.setOnClickListener{
                /**
                 * Con el let garantizo que si el valor no esn uelo se ejecuta la función (lambda)
                 */
                onItemClickListener?.let{
                    it(myProduct)
                }
            }

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
        holder.bind(products[position],onItemClickListener)
    }

    override fun getItemCount(): Int = products.size
}