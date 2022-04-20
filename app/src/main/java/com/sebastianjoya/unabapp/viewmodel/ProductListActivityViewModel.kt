package com.sebastianjoya.unabapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.repository.ProductRepository
import com.sebastianjoya.unabapp.view.ProductAdapter

class ProductListActivityViewModel(application: Application):AndroidViewModel(application) {
    var products:ArrayList<Product> = arrayListOf()
    private val productRepository:ProductRepository = ProductRepository(application)

    init{
        loadProducts()
    }

    fun loadProducts(){
        products = productRepository.getAllLocal()
    }

    fun deleteProduct(myProduct: Product){
        productRepository.deleteLocal(myProduct)
        loadProducts()
    }


}