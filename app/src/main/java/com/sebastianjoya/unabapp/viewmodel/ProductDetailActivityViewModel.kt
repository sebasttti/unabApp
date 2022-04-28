package com.sebastianjoya.unabapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.repository.ProductRepository

class ProductDetailActivityViewModel(application: Application):AndroidViewModel(application) {
    private val productRepository: ProductRepository = ProductRepository(application)
    lateinit var product:LiveData<Product>

    fun fetchProduct(productKey: Int){
        product = productRepository.getByKeyLocal(productKey)
    }
}