package com.sebastianjoya.unabapp.model.repository

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.local.StoreAppDb
import com.sebastianjoya.unabapp.model.local.dao.ProductDAO
import com.sebastianjoya.unabapp.model.remote.StoreAppAPI
import com.sebastianjoya.unabapp.model.remote.service.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ProductRepository(myContext: Context) {

    private val PRODUCT_COLLECTION:String = "products"

    private val db:StoreAppDb = StoreAppDb.getInstance(myContext)
    private val productDAO:ProductDAO = db.productDAO()

    var products:MutableLiveData<List<Product>> = MutableLiveData()
    var product:MutableLiveData<Product> = MutableLiveData()


    private val firestore : FirebaseFirestore = Firebase.firestore

    private val api:Retrofit = StoreAppAPI.getInstance()!!
    private val productService:ProductService = api.create(ProductService::class.java)

    init{
        //loadAllLocal()

    }

    fun loadAllFirestore(){

        firestore.collection(PRODUCT_COLLECTION).get().addOnSuccessListener { result ->

                val productList: ArrayList<Product> = arrayListOf()
                if (!result.isEmpty){
                    for(document in result.documents){
                        val myProduct: Product? = document.toObject(Product::class.java)
                        myProduct?.let {
                            it.id = document.id
                            productList.add(it)
                        }

                    }
                }

                products.value=productList


        }
    }

    fun listenAllFirestore(){
        firestore.collection(PRODUCT_COLLECTION).addSnapshotListener{snapshot,e->
            snapshot?.let {
                val productList: ArrayList<Product> = arrayListOf()
                if (!snapshot.isEmpty){

                    for(document in snapshot.documents){
                        val myProduct: Product? = document.toObject(Product::class.java)
                        myProduct?.let {
                            it.id = document.id
                            productList.add(it)
                        }

                    }

                }

                products.value=productList
            }
        }
    }

    fun loadAllAPI(){
        productService.getAll().enqueue(object : Callback<Map<String,Product>?>{

            override fun onResponse(
                call: Call<Map<String, Product>?>,
                response: Response<Map<String, Product>?>
            ) {
                val productList: ArrayList<Product> = arrayListOf()
                response.body()?.let{
                    it.forEach{ (id,product)->
                        product.id=id
                        productList.add(product)

                    }

                    products.value=productList
                }
            }

            override fun onFailure(call: Call<Map<String, Product>?>, t: Throwable) {
                println("Error ${t.message}")
            }

        })
    }

    fun loadFakeData(){
        productDAO.apply{
            add(Product(name="Monitor",value="600000",description = "Este es un monitor",urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_906150-MLA47751021005_102021-F.webp"))
            add(Product(name="Mouse",value="50000",description = "Este es un mouse", urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_758962-MLA45387177473_032021-F.webp"))
            add(Product(name="Teclado",value="100000",description = "Este es un teclado", urlImage = "https://http2.mlstatic.com/D_NQ_NP_2X_627725-MCO41476280647_042020-F.webp"))
        }

    }

    fun loadAllLocal(){
        val productsList = productDAO.getAll()
        products.value=productsList
    }

    fun getByKeyLocal(key:Int){
        product.value =  productDAO.getByKey(key)
    }

    fun getByIdFirestore(id:String){
        firestore.collection(PRODUCT_COLLECTION).document(id).get().addOnSuccessListener {
            result->
            val myProduct:Product? =result.toObject(Product::class.java)
            myProduct?.let{
                it.id = id
                product.value = it
            }
        }
    }

    fun getByIdAPI(id:String){
        productService.getById(id).enqueue(object : Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                response.body()?.let{
                    it.id=id
                    product.value=it
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {

            }


        })
    }

    fun addAPI(myProduct: Product):MutableLiveData<String>{
        val productIdObserver:MutableLiveData<String> = MutableLiveData()
        productService.add(myProduct).enqueue(object : Callback<Map<String,String>>{
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                response.body()?.let{
                    myProduct.id = it["name"]!!

                    productIdObserver.value = myProduct.id

                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                productIdObserver.value=""
            }

        })
        return productIdObserver
    }

    fun addLocal(myProduct: Product){
        productDAO.add(myProduct)

    }

    fun addFirestore(myProduct: Product, photoUri: Uri?):LiveData<String>{
        val productIdObserver:MutableLiveData<String> = MutableLiveData();

        photoUri?.let{

           val storageReference = Firebase.storage.reference.child(PRODUCT_COLLECTION)
           val time = SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US).format(Date())
           val name = time + myProduct.name + ".jpg"
           storageReference.child(name).putFile(photoUri).addOnSuccessListener {
               it.storage.downloadUrl.addOnSuccessListener {
                   url->
                   myProduct.urlImage = url.toString()
                   firestore.collection(PRODUCT_COLLECTION).add(myProduct).addOnSuccessListener {
                       response->
                       productIdObserver.value = response.id
                   }.addOnFailureListener{
                       productIdObserver.value=""
                   }

               }
           }
        }?:run{

            firestore.collection(PRODUCT_COLLECTION).add(myProduct).addOnSuccessListener {
                    response->
                productIdObserver.value = response.id
            }.addOnFailureListener{
                productIdObserver.value=""
            }
        }

        return productIdObserver
    }



    fun updateLocal(myProduct: Product){
        productDAO.update(myProduct)
    }

    fun updateFirestore(myProduct: Product):LiveData<Boolean>{
        val stateUpdate:MutableLiveData<Boolean> = MutableLiveData()
        firestore.collection(PRODUCT_COLLECTION).document(myProduct.id).set(myProduct).addOnSuccessListener {
            stateUpdate.value = true
        }.addOnFailureListener{
            stateUpdate.value=false
        }

        return stateUpdate
    }

    fun updateAPI(myProduct: Product):MutableLiveData<Boolean>{

        val productState:MutableLiveData<Boolean> = MutableLiveData()

        productService.update(myProduct.id,myProduct).enqueue(object : Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                response.body()?.let{
                  productState.value=true
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                productState.value=false
            }
        })

        return productState
    }



    fun deleteLocal(myProduct: Product){
        productDAO.delete(myProduct)
        loadAllLocal()

    }

    fun deleteFirestore(myProduct: Product):LiveData<Boolean>{
        val stateDelete:MutableLiveData<Boolean> = MutableLiveData()
        firestore.collection(PRODUCT_COLLECTION).document(myProduct.id).delete().addOnSuccessListener {
            loadAllFirestore()
            stateDelete.value=true
        }.addOnFailureListener{
            stateDelete.value=false
        }

        return stateDelete

    }


    fun deleteAPI(myProduct: Product):MutableLiveData<Boolean>{

        val productState:MutableLiveData<Boolean> = MutableLiveData()

        productService.delete(myProduct.id).enqueue(object : Callback<Unit>{
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                response.body()?.let{
                    productState.value=true
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                productState.value=false
            }
        })

        return productState
    }

}