package com.sebastianjoya.unabapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ActivityProductFormBinding
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.viewmodel.ProductFormActivityViewModel
import com.sebastianjoya.unabapp.viewmodel.ProductListActivityViewModel


class ProductFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductFormBinding
    lateinit var viewModel: ProductFormActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bundle:Bundle? = intent.extras
        title=bundle?.getString("title")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_form)

        viewModel = ViewModelProvider(this)[ProductFormActivityViewModel::class.java]

        var myProduct:Product? = intent.getSerializableExtra("product") as Product?

        myProduct?.let {
            binding.buProductFormConfirm.text = "CONFIRMAR"

            binding.buProductFormConfirm.setOnClickListener{
                viewModel.update(binding.product as Product)
                finish()
            }


        }?:run{
            myProduct = Product(name = "",description = "",value = "",urlImage = "")

            binding.buProductFormConfirm.setOnClickListener{
                viewModel.add(binding.product as Product)
                finish()
            }
        }

        binding.product = myProduct



        binding.buProductFormReturn.setOnClickListener{
            finish()
        }

    }



}