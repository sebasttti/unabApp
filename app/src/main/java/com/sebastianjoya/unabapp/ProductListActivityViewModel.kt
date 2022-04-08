package com.sebastianjoya.unabapp

import androidx.lifecycle.ViewModel

class ProductListActivityViewModel:ViewModel() {
    val products:ArrayList<Product> = arrayListOf()
    val adapter:ProductAdapter =  ProductAdapter(products)

    fun loadProducts(){
        products.apply{
            clear()
            add(Product(name="Monitor",value="600000"))
            add(Product(name="Mouse",value="50000"))
            add(Product(name="Teclado",value="100000"))
        }
    }

    fun refreshData(){
        adapter.refresh(products)
    }


}