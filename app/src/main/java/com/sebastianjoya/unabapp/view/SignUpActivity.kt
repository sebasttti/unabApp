package com.sebastianjoya.unabapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sebastianjoya.unabapp.R
import com.sebastianjoya.unabapp.databinding.ActivitySignUpBinding
import com.sebastianjoya.unabapp.model.entity.User
import com.sebastianjoya.unabapp.viewmodel.SignUpActivityViewModel

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    lateinit var viewModel: SignUpActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "REGISTRO DE USUARIO"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        viewModel = ViewModelProvider(this)[SignUpActivityViewModel::class.java]

        binding.viewModel = viewModel

        binding.buSignUpConfirm.setOnClickListener{
            viewModel.signUp().observe(this){
                it?.let{
                    login(it)
                }?:run{
                    Toast.makeText(applicationContext,"Error al crear el usuario",Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.buSignUpReturn.setOnClickListener{
            finish()
        }
    }

    private fun login(it: User) {
        val preferences: SharedPreferences = getSharedPreferences("unabApp.pref",MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putBoolean("login",true)
        editor.apply()

        Toast.makeText(this,"Login correcto",Toast.LENGTH_SHORT).show()
        goToProductsView()
    }

    private fun goToProductsView() {
        val intentSignUp = Intent(applicationContext, ProdutsListActivity::class.java)
        intentSignUp.apply{
            //putExtra("message","hola")
            //putExtra("data",viewModel.user.email)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intentSignUp)
    }
}