package com.sebastianjoya.unabapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sebastianjoya.unabapp.model.entity.User
import com.sebastianjoya.unabapp.model.repository.UserRepository

class MainActivityViewModel:ViewModel() {
    val user: User = User(email = "juan@correo.com")
    var password:String = "123456789"
    private val userRepository:UserRepository = UserRepository()

    fun login():LiveData<User?>{
        return userRepository.login(user.email,password)
    }
}