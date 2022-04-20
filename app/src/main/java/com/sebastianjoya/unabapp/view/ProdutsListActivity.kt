package com.sebastianjoya.unabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sebastianjoya.unabapp.viewmodel.ProductListActivityViewModel
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ActivityProdutsListBinding

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

        adapter = ProductAdapter(viewModel.products)

        binding.adapter = adapter

        /**
         * Función para saber qué producto elegí
         */
        adapter.onItemClickListener={
            Toast.makeText(applicationContext,it.name,Toast.LENGTH_SHORT).show()

            val intentDetail = Intent(applicationContext,ProductDetailActivity::class.java)
            intentDetail.putExtra("product",it)

            startActivity(intentDetail)

        }

        adapter.onItemLongClickListener={

            viewModel.deleteProduct(it)

            adapter.refresh(viewModel.products)

            Toast
                .makeText(applicationContext,"Producto ${it.name} eliminado",Toast.LENGTH_SHORT)
                .show()
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
        super.onResume()
        viewModel.loadProducts()
        adapter.refresh(viewModel.products)
    }



}


