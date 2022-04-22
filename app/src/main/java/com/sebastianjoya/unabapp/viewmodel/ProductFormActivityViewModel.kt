package com.sebastianjoya.unabapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.repository.ProductRepository

class ProductFormActivityViewModel(application: Application): AndroidViewModel(application){

    private val productRepository: ProductRepository = ProductRepository(application)

    fun add(myProduct: Product){
        productRepository.addLocal(myProduct)
    }

    fun update(myProduct: Product){
        productRepository.updateLocal(myProduct)
    }

    fun getImage(urlString:String){

    }

}