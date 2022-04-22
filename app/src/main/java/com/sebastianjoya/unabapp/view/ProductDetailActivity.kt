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
    lateinit var viewModel: ProductDetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myProduct:Product = intent.getSerializableExtra("product") as Product

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        viewModel = ViewModelProvider(this)[ProductDetailActivityViewModel::class.java]

        viewModel.product = myProduct

        binding.product = viewModel.product

        binding.buProductDetailReturn.setOnClickListener{
            finish()
        }

        binding.buProductDetailEdit.setOnClickListener{
            val intentSignUp = Intent(applicationContext, ProductFormActivity::class.java)
            intentSignUp.apply{
                putExtra("product",myProduct)
            }
            startActivity(intentSignUp)
        }

    }

    override fun onResume(){
        super.onResume()
    }
}