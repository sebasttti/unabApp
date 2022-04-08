package com.sebastianjoya.unabapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sebastianjoya.unabapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.viewModel = viewModel

//        binding.tvTitleLogin.text="Modificado por código"

//        var newUser: User = User(email = "jjoya449@unab.edu.co", password = "unab2022")
//        binding.dataUser = newUser
//        binding.dataTitle = "Modificado por codigo otra vez"

        binding.buLoginLogin.setOnClickListener{

            if (viewModel.login()){
                Toast.makeText(this,"Login correcto",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Login inválido",Toast.LENGTH_SHORT).show()
            }

//            println("Hola mundo")
//            Toast.makeText(this,"Login oprimido",Toast.LENGTH_SHORT).show()
//            println("${binding.dataUser?.name}")


//            if (binding.dataUser?.email == getString(R.string.validEmail) &&
//                binding.dataUser?.password == getString(R.string.validPassword)
//                    ){
//                Toast.makeText(this,"Login correcto",Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this,"Login inválido",Toast.LENGTH_SHORT).show()
//            }
        }
    }
}