package com.sebastianjoya.unabapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ActivityMainBinding
import com.sebastianjoya.unabapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = getSharedPreferences("unabApp.pref", MODE_PRIVATE)

        //preferences.edit().remove("login").apply()

        if (preferences.getBoolean("login",false)){

            goToProductsView()
        }


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.viewModel = viewModel

//        binding.tvTitleLogin.text="Modificado por código"

//        var newUser: User = User(email = "jjoya449@unab.edu.co", password = "unab2022")
//        binding.dataUser = newUser
//        binding.dataTitle = "Modificado por codigo otra vez"

        binding.buLoginLogin.setOnClickListener{


            if (viewModel.login()){

                val preferences: SharedPreferences = getSharedPreferences("unabApp.pref",MODE_PRIVATE)
                val editor:SharedPreferences.Editor = preferences.edit()
                editor.putBoolean("login",true)
                editor.apply()

                Toast.makeText(this,"Login correcto",Toast.LENGTH_SHORT).show()
                goToProductsView()
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

        binding.buLoginSignup.setOnClickListener{

            /**
             * Evento para llamar a otra pantalla
             */


        }
    }

    fun goToProductsView(){
        val intentSignUp = Intent(applicationContext, ProdutsListActivity::class.java)
        /*intentSignUp.apply{
            putExtra("message","hola")
            putExtra("data",viewModel.user.email)
        }*/
        startActivity(intentSignUp)
    }
}