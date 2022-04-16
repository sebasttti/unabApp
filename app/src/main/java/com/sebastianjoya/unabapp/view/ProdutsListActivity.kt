package com.sebastianjoya.unabapp.view

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

        binding.viewModel = viewModel

        viewModel.loadProducts()

        viewModel.refreshData()

        /**
         * Función para saber qué producto elegí
         */
        viewModel.adapter.onItemClickListener={
            Toast.makeText(applicationContext,it.name,Toast.LENGTH_SHORT).show()
        }

        binding.buListReturn.setOnClickListener{
            /**
             * Para finalizar la actividad se usa finsih()
             */
            finish()
        }
    }


}


