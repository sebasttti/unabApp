package com.sebastianjoya.unabapp.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sebastianjoya.unabapp.model.entity.User

class UserRepository {
    private val firestore = Firebase.firestore
    private val auth = Firebase.auth
    private val USER_COLLECTION = "users"
    private val userObserver:MutableLiveData<User?> = MutableLiveData()

    fun signUp(user:User, password:String):LiveData<User?>{
        auth.createUserWithEmailAndPassword(user.email,password).addOnSuccessListener {
            user.id = auth.uid!!
            createFirestore(user)


        }.addOnFailureListener{
            println("SignUp: ${it.message}")
            userObserver.value = null
        }

        return userObserver
    }

    private fun createFirestore(user:User){
        firestore.collection(USER_COLLECTION).document(user.id).set(user).addOnSuccessListener {
            userObserver.value = user
        }.addOnFailureListener{
            userObserver.value = null
        }
    }

    fun login(email:String,password: String):LiveData<User?>{
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            getByIdFirestore(auth.uid)
        }.addOnFailureListener{
            userObserver.value=null
        }
        return userObserver
    }

    private fun getByIdFirestore(uid: String?) {
        uid?.let{id->
            firestore.collection(USER_COLLECTION).document(id).get().addOnSuccessListener { response->
                val user:User? = response.toObject(User::class.java)
                user?.let { userInside->
                userInside.id = id
                userObserver.value = user
                }
            }.addOnFailureListener{
                userObserver.value = null
            }
        }?:run{
            userObserver.value=null
        }

    }

}