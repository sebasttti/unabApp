package com.sebastianjoya.unabapp.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ActivityProductFormBinding
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.viewmodel.ProductFormActivityViewModel
import com.sebastianjoya.unabapp.viewmodel.ProductListActivityViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ProductFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductFormBinding
    lateinit var viewModel: ProductFormActivityViewModel
    lateinit var resultGallery: ActivityResultLauncher<Intent>
    lateinit var resultCamera: ActivityResultLauncher<Intent>
    var photoUri:Uri? = null

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
            myProduct = Product()

            binding.buProductFormConfirm.setOnClickListener{

                binding.buProductFormConfirm.isEnabled = false
                viewModel.add(binding.product as Product,photoUri).observe(this){
                    id->
                    binding.buProductFormConfirm.isEnabled = true
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

        resultGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) run {
                photoUri = it.data!!.data!!
                Glide.with(applicationContext).load(photoUri).into(binding.ivProductFormImg)
            }
        }

        resultCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) run {
                Glide.with(applicationContext).load(photoUri).into(binding.ivProductFormImg)
            }

        }



        binding.buProductFormReturn.setOnClickListener{
            //finish()
        }

        binding.ibProductFormGallery.setOnClickListener{
           val galleryItem = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultGallery.launch(galleryItem)
            //galleryItem.resolveActivity(packageManager)?.let{
           //     startActivity(galleryItem)
           //}
        }

        binding.ibProductFormCamera.setOnClickListener{
            val cameraItem = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            var photoFile:File? = null
            try{
                photoFile = createImage()
            }catch(e:IOException){

            }

            photoFile?.let{
                photoUri = FileProvider.getUriForFile(applicationContext,"com.sebastianjoya.unabapp.fileprovider",photoFile)
                cameraItem.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                resultCamera.launch(cameraItem)
            }

            //resultGallery.launch(galleryItem)

        }



    }

    private fun createImage(): File? {
        var timeStamp = SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US).format(Date())
        val storeAppDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(timeStamp, ".jpg",storeAppDir)
    }





}