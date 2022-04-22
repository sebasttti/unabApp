package com.sebastianjoya.unabapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ActivityProductDetailBinding
import com.sebastianjoya.unabapp.databinding.ActivityProdutsListBinding
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.viewmodel.ProductDetailActivityViewModel
import com.sebastianjoya.unabapp.viewmodel.ProductListActivityViewModel

class ProductDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDetailBinding
    lateinit var viewModel:ProductDetailActivityViewModel
    private var productKey = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        viewModel = ViewModelProvider(this)[ProductDetailActivityViewModel::class.java]

        productKey = intent.getIntExtra("productKey",0)

        viewModel.fetchProduct(productKey)

        reloadProduct()

        binding.buProductDetailReturn.setOnClickListener{
            finish()
        }

        binding.buProductDetailEdit.setOnClickListener{
            val intentSignUp = Intent(applicationContext, ProductFormActivity::class.java)
            intentSignUp.apply{
                putExtra("product",viewModel.product)
            }
            startActivity(intentSignUp)
        }

    }

    override fun onResume(){
        reloadProduct()
        super.onResume()

    }

    private fun reloadProduct(){
        viewModel.fetchProduct(productKey)
        binding.product = viewModel.product
    }
}