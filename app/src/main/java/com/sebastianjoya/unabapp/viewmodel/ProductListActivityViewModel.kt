package com.sebastianjoya.unabapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sebastianjoya.unabapp.model.Product
import com.sebastianjoya.unabapp.view.ProductAdapter

class ProductListActivityViewModel:ViewModel() {
    val products:ArrayList<Product> = arrayListOf()
    val adapter: ProductAdapter =  ProductAdapter(products)

    fun loadProducts(){
        products.apply{
            clear()
            add(Product(name="Monitor",value="600000",urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_906150-MLA47751021005_102021-F.webp"))
            add(Product(name="Mouse",value="50000"))
            add(Product(name="Teclado",value="100000"))
        }
    }

    fun refreshData(){
        adapter.refresh(products)
    }


}