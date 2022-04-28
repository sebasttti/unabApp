package com.sebastianjoya.unabapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.repository.ProductRepository

class ProductFormActivityViewModel(application: Application): AndroidViewModel(application){

    private val productRepository: ProductRepository = ProductRepository(application)
    var products: LiveData<List<Product>> = productRepository.products

    fun add(myProduct: Product){
        productRepository.addLocal(myProduct)
    }

    fun update(myProduct: Product){
        productRepository.updateLocal(myProduct)
    }

    fun getImage(urlString:String){

    }

}