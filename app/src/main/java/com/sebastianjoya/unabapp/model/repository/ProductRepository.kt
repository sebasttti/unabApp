package com.sebastianjoya.unabapp.model.repository

import android.content.Context
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.local.StoreAppDb
import com.sebastianjoya.unabapp.model.local.dao.ProductDAO

class ProductRepository(myContext: Context) {
    private val db:StoreAppDb = StoreAppDb.getInstance(myContext)
    private val productDAO:ProductDAO = db.productDAO()
    private var products:ArrayList<Product> = arrayListOf()

    private fun loadFakeData(){
        productDAO.apply{
            add(Product(name="Monitor",value="600000",description = "Este es un monitor",urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_906150-MLA47751021005_102021-F.webp"))
            add(Product(name="Mouse",value="50000",description = "Este es un mouse", urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_758962-MLA45387177473_032021-F.webp"))
            add(Product(name="Teclado",value="100000",description = "Este es un teclado", urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_627725-MCO41476280647_042020-F.webp"))
        }
        loadAllLocal()
    }

    fun getAllLocal(): ArrayList<Product> {
        loadAllLocal()
        return productDAO.getAll() as ArrayList<Product>
    }

    fun loadAllLocal(){
        products = productDAO.getAll() as ArrayList<Product>

        if (products.isEmpty()){
            loadFakeData()
        }
    }

    fun getByKeyLocal(key:Int):Product{
        return productDAO.getByKey(key)
    }

    fun addLocal(myProduct: Product){
        productDAO.add(myProduct)
        loadAllLocal()
    }

    fun updateLocal(myProduct: Product){
        productDAO.update(myProduct)
        loadAllLocal()
    }

    fun deleteLocal(myProduct: Product){
        productDAO.delete(myProduct)
        loadAllLocal()
    }



}