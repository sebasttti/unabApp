package com.sebastianjoya.unabapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sebastianjoya.unabapp.model.entity.User

class MainActivityViewModel:ViewModel() {
    val user: User = User(email = "juanjoya@gmail.com",password = "123456")

    fun login():Boolean{

        println(user.email)
        println(user.password)

        return user.email=="juanjoya@gmail.com" && user.password == "123456"
    }
}