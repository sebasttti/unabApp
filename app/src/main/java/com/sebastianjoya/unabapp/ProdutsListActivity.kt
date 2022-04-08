package com.sebastianjoya.unabapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sebastianjoya.unabapp.databinding.ActivityProdutsListBinding

class ProdutsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityProdutsListBinding
    lateinit var viewModel: ProductListActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_produts_list)

        viewModel = ViewModelProvider(this)[ProductListActivityViewModel::class.java]

        binding.viewModel = viewModel

        viewModel.loadProducts()

        viewModel.refreshData()
    }
}


