package com.sebastianjoya.unabapp.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.repository.ProductRepository

class ProductFormActivityViewModel(application: Application): AndroidViewModel(application){

    private val productRepository: ProductRepository = ProductRepository(application)
    var products: LiveData<List<Product>> = productRepository.products

    fun add(myProduct: Product,photoUri:Uri?):LiveData<String>{
        //productRepository.addLocal(myProduct)
        return productRepository.addFirestore(myProduct,photoUri)
    }

    fun update(myProduct: Product):LiveData<Boolean>{
        //productRepository.updateLocal(myProduct)
        return productRepository.updateFirestore(myProduct)
    }

    fun getImage(urlString:String){

    }

}