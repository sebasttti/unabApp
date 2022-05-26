package com.sebastianjoya.unabapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sebastianjoya.unabapp.viewmodel.ProductListActivityViewModel
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ActivityProdutsListBinding
import com.sebastianjoya.unabapp.model.entity.Product

class ProdutsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityProdutsListBinding
    lateinit var viewModel: ProductListActivityViewModel
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        var bundle:Bundle? = intent.extras
        var message:String? = bundle?.getString("message")
        var data:String? = bundle?.getString("data")

        /**
         * La variable title pone el titulo sobre la app
         */
        title = "LISTADO DE PRODUCTOS"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_produts_list)

        viewModel = ViewModelProvider(this)[ProductListActivityViewModel::class.java]

        adapter = ProductAdapter(arrayListOf())

        binding.adapter = adapter

        //=========================

        loadData()

        //===========================

        /**
         * Función para saber qué producto elegí
         */
        adapter.onItemClickListener={
            Toast.makeText(applicationContext,it.name,Toast.LENGTH_SHORT).show()

            val intentDetail = Intent(applicationContext,ProductDetailActivity::class.java)
            intentDetail.putExtra("productKey",it.key)
            intentDetail.putExtra("productId",it.id)

            startActivity(intentDetail)

        }

        adapter.onItemLongClickListener={

            viewModel.deleteProduct(it).observe(this){
                state->
                if (state){
                    Toast
                        .makeText(applicationContext,"Producto ${it.name} eliminado",Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast
                        .makeText(applicationContext,"Problema al eliminar el producto ${it.name}",Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }

        binding.buListReturn.setOnClickListener{
            /**
             * Para finalizar la actividad se usa finsih()
             */
            finish()
        }

        binding.buListAdd.setOnClickListener{
            val intentDetail = Intent(applicationContext,ProductFormActivity::class.java)
            intentDetail.putExtra("title","AGREGAR PRODUCTO")

            startActivity(intentDetail)

        }
    }

    override fun onResume(){
        viewModel.loadData()
        super.onResume()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_logout ->{
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout(){
        val preferences:SharedPreferences = getSharedPreferences("unabApp.pref", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

    }

    private fun loadData(){
        //viewModel.loadData()
        viewModel.products.observe(this){

            //if (it.isEmpty()){
                //viewModel.loadFakeData()
            //}

            adapter.refresh(it as ArrayList<Product>)
        }
    }

}


