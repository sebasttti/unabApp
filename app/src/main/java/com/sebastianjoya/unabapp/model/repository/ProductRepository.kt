package com.sebastianjoya.unabapp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.local.StoreAppDb
import com.sebastianjoya.unabapp.model.local.dao.ProductDAO

class ProductRepository(myContext: Context) {
    private val db:StoreAppDb = StoreAppDb.getInstance(myContext)
    private val productDAO:ProductDAO = db.productDAO()
    var products:LiveData<List<Product>> = productDAO.getAll()

    init{
        //loadAllLocal()
    }

    fun loadFakeData(){
        productDAO.apply{
            add(Product(name="Monitor",value="600000",description = "Este es un monitor",urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_906150-MLA47751021005_102021-F.webp"))
            add(Product(name="Mouse",value="50000",description = "Este es un mouse", urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_758962-MLA45387177473_032021-F.webp"))
            add(Product(name="Teclado",value="100000",description = "Este es un teclado", urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_627725-MCO41476280647_042020-F.webp"))
        }

    }

    fun loadAllLocal(){
        productDAO.apply{
            add(Product(name="Ejemplo",value="600000",description = "Este es un ejemplo"))
        }
        products = productDAO.getAll()
    }

    fun getByKeyLocal(key:Int):LiveData<Product>{
        return productDAO.getByKey(key)
    }

    fun addLocal(myProduct: Product){
        productDAO.add(myProduct)

    }

    fun updateLocal(myProduct: Product){
        productDAO.update(myProduct)

    }

    fun deleteLocal(myProduct: Product){
        productDAO.delete(myProduct)

    }



}