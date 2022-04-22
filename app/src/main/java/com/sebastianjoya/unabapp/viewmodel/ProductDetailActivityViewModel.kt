package com.sebastianjoya.unabapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.repository.ProductRepository

class ProductDetailActivityViewModel(application: Application):AndroidViewModel(application) {
    var product = Product(name = "",value = "",description = "")
    private val productRepository: ProductRepository = ProductRepository(application)

    fun fetchProduct(productKey: Int){
        product = productRepository.getByKeyLocal(productKey)
    }
}