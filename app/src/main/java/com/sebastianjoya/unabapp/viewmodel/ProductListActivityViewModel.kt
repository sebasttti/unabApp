package com.sebastianjoya.unabapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.repository.ProductRepository
import com.sebastianjoya.unabapp.view.ProductAdapter

class ProductListActivityViewModel(application: Application):AndroidViewModel(application) {

    private val productRepository:ProductRepository = ProductRepository(application)
    var products:LiveData<List<Product>> = productRepository.products

    fun deleteProduct(myProduct: Product) {
        productRepository.deleteLocal(myProduct)
    }

    fun addProduct(newProduct: Product){
        //val newProduct:Product = Product(name = "ejemplo", value = "34575", description = "Producto de ejemplo")
        productRepository.addLocal(newProduct)
    }

    fun loadData(){
        productRepository.loadAllLocal()
    }

    fun loadFakeData(){
        productRepository.loadFakeData()
    }




}