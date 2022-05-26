package com.sebastianjoya.unabapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class StoreAppAPI {
    companion object{
        private var instance:Retrofit? = null
        private const val url:String = "https://unabapp-mgads-2022-default-rtdb.firebaseio.com/"


        fun getInstance():Retrofit?{
            if (instance == null){
                instance = Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }
    }
}