package com.sebastianjoya.unabapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sebastianjoya.unabapp.model.entity.Product
import com.sebastianjoya.unabapp.model.local.dao.ProductDAO

@Database(entities = [Product::class],version=1)
abstract class StoreAppDb:RoomDatabase() {

    abstract fun productDAO(): ProductDAO

    companion object{
        // forma de escribir clases estaticas en kotlin
        @Volatile
        private var INSTANCE: StoreAppDb? = null
        fun getInstance(myContext:Context):StoreAppDb{
            var instance = INSTANCE

            if (instance == null){
                instance = Room.databaseBuilder(myContext,StoreAppDb::class.java,"storeApp.db")
                            .allowMainThreadQueries()
                            .build()
            }

            return instance
        }
    }

}