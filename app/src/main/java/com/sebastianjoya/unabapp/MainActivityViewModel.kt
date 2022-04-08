package com.sebastianjoya.unabapp

import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    val user:User = User(email = "juanjoya@gmail.com",password = "123456")

    fun login():Boolean{
        return user.email=="jjoya449@unab.edu.co" && user.password == "mgdas2022"
    }
}