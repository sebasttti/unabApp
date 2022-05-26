package com.sebastianjoya.unabapp.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
                viewModel.update(binding.product as Product).observe(this){
                    state->
                    if(state){
                        finish()
                    }else{
                        Toast
                            .makeText(applicationContext,"Problema al modificar el producto",
                                Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }


        }?:run{
            myProduct = Product(name = "",description = "",value = "",urlImage = "")

            binding.buProductFormConfirm.setOnClickListener{
                viewModel.add(binding.product as Product).observe(this){
                    id->
                    if (id != ""){
                        finish()
                    }else{
                        Toast
                            .makeText(applicationContext,"Problema al agregar el producto",
                                Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }

        binding.product = myProduct



        binding.buProductFormReturn.setOnClickListener{
            finish()
        }

    }

    private fun sendDataBackToPreviousActivity(thisProduct: Product) {
        val intent = Intent().apply {
            putExtra("product", thisProduct)
            // Put your data here if you want.
        }
        setResult(Activity.RESULT_OK, intent)
    }



}