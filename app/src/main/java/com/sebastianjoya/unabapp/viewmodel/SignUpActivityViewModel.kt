package com.sebastianjoya.unabapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sebastianjoya.unabapp.model.entity.User
import com.sebastianjoya.unabapp.model.repository.UserRepository

class SignUpActivityViewModel:ViewModel() {
    var user:User = User()
    var password:String = ""
    private val userRepository = UserRepository()

    fun signUp(): LiveData<User?> {
        return userRepository.signUp(user,password)
    }
}